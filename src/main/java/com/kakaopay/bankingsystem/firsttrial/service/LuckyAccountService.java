package com.kakaopay.bankingsystem.firsttrial.service;

import com.kakaopay.bankingsystem.firsttrial.entity.Account;
import com.kakaopay.bankingsystem.firsttrial.entity.Token;
import com.kakaopay.bankingsystem.firsttrial.entity.Transaction;
import com.kakaopay.bankingsystem.firsttrial.entity.TransactionStatus;
import com.kakaopay.bankingsystem.firsttrial.exception.LuckyAccountReadResponse;
import com.kakaopay.bankingsystem.firsttrial.exception.ReadRuleViolationException;
import com.kakaopay.bankingsystem.firsttrial.exception.WithdrawFailureException;
import com.kakaopay.bankingsystem.firsttrial.exception.WithdrawRuleViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// lesson2. 26:30


@Service
@RequiredArgsConstructor
public class LuckyAccountService {
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final TokenService tokenService;

    public Long create(LuckyAccountCreateRequest request) {
        LocalDateTime requestAt = request.getRequestAt();

        Token token = generateToken(requestAt.plusDays(7));

        Long userId = request.getUserId();
        long amount = request.getAmount();
        int withdrawLimit = request.getWithdrawLimit();

        AccountCreateRequest accountCreateRequest = AccountCreateRequest.builder()
                .token(token)
                .ownerId(userId)
                .roomId(request.getRoomId())
                .createdAt(requestAt)
                .withdrawExpireAt(requestAt.plusMinutes(10))
                .lookupExpireAt(requestAt.plusDays(7))
                .build();

        Account account = accountService.create(accountCreateRequest);
        transactionService.deposit(account, request.getAmount(), userId);
        for (int i = 0; i < withdrawLimit; i++) {
            transactionService.withdrawStandby(account, amount / withdrawLimit);
        }
        return account.getId();
    }

    @Transactional
    public void withdraw(LuckyAccountWithdrawRequest request) {
        Token token = tokenService.getValidToken(request.getTokenName());
        Account account = accountService.findByToken(token);
        Long userId = request.getUserId();
        if(!account.getRoomId().equals(request.getRoomId())) {
            throw new WithdrawRuleViolationException("일치하지 않는 방 번호입니다.");
        }
        if (request.getRequestedAt().isAfter(account.getWithdrawExpiredAt())) {
            throw new WithdrawRuleViolationException("뿌리기가 만료되었습니다.");
        }
        if(account.getOwnerId().equals(userId)) {
            throw new WithdrawRuleViolationException("자신이 생성한 뿌리기는 받을 수 없습니다.");
        }

        List<Transaction> transactions = transactionService.findByAccount(account);
        if (hasTransactionAlready(request, transactions)) {
            throw new WithdrawRuleViolationException("이미 받은 뿌리기는 중복으로 다시 받을 수 없습니다.");
        }

        Transaction withdrawStandby = transactions.stream()
                .filter(transaction -> TransactionStatus.WITHDRAW_STANDBY == transaction.getStatus())
                .findFirst()
                .orElseThrow(() -> new WithdrawFailureException("더 이상 남은 뿌리기가 없습니다."));

        withdrawStandby.toNextStatus(TransactionStatus.WITHDRAW_COMPLETED, userId);
    }

    public LuckyAccountReadResponse read(LuckyAccountReadRequest request) {
        Token token = tokenService.getValidToken(request.getTokenName());
        Account account = accountService.findByToken(token);

        if (!account.getOwnerId().equals(request.getUserId())) {
            throw new ReadRuleViolationException("뿌린 사람만 조회를 할 수 있습니다.");
        }

        List<Transaction> transactions = transactionService.findByAccount(account);

        long totalAmount = 0;
        Map<Long, Long> userToAmount = new HashMap<>();
        for (Transaction transaction: transactions) {
            totalAmount += transaction.getAmount();
            if (transaction.getStatus().equals(TransactionStatus.WITHDRAW_COMPLETED)) {
                userToAmount.put(transaction.getUserId(), transaction.getAmount());
            }
        }

        return new LuckyAccountReadResponse(
                account.getCreateAt(),
                totalAmount,
                userToAmount.values().stream().mapToLong(amount -> amount).sum(),
                userToAmount
        );
    }

    private boolean hasTransactionAlready(LuckyAccountWithdrawRequest request, List<Transaction> transactions) {
        return transactions.stream().anyMatch(transaction -> request.getUserId().equals(transaction.getUserId()));
    }

    private Token generateToken(LocalDateTime expiredAt) {
        return tokenService.generateToken(expiredAt);
    }
}
