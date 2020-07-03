package com.kakaopay.bankingsystem.domain.service;

import com.kakaopay.bankingsystem.domain.entity.Account;
import com.kakaopay.bankingsystem.domain.entity.Transaction;
import com.kakaopay.bankingsystem.domain.entity.TransactionStatus;
import com.kakaopay.bankingsystem.domain.exception.WithdrawFailureException;
import com.kakaopay.bankingsystem.domain.exception.WithdrawRuleViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;


// lesson2. 26:30


@Service
@RequiredArgsConstructor
public class LuckyAccountService {
    private final AccountService accountService;
    private final TransactionService transactionService;

    public void create(LuckyAccountCreateRequest request) {
        Long userId = request.getUserId();
        long amount = request.getAmount();
        int withdrawLimit = request.getWithdrawLimit();
        LocalDateTime requestAt = request.getRequestAt();

        AccountCreateRequest accountCreateRequest = AccountCreateRequest.builder()
                .token("abc")
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
    }

    @Transactional
    public void withdraw(LuckyAccountWithdrawRequest request) {
        Account account = accountService.findByToken(request.getToken());
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

    private boolean hasTransactionAlready(LuckyAccountWithdrawRequest request, List<Transaction> transactions) {
        return transactions.stream().anyMatch(transaction -> request.getUserId().equals(transaction.getUserId()));
    }
}
