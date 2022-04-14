package com.kakaopay.bankingsystem.firsttrial.service;

import com.kakaopay.bankingsystem.firsttrial.entity.Account;
import com.kakaopay.bankingsystem.firsttrial.entity.Transaction;
import com.kakaopay.bankingsystem.firsttrial.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;

    public List<Transaction> findByAccount(Account account) {
        return transactionRepository.findByAccount(account);
    }

    public Transaction deposit(Account account, long amount, Long userId) {
        return transactionRepository.save(Transaction.depositCompleted(account, amount, userId, LocalDateTime.now()));
    }

    public Transaction withdrawStandby(Account account, long amount) {
        return transactionRepository.save(Transaction.withdrawStandby(account, amount, null, LocalDateTime.now()));
    }
}
