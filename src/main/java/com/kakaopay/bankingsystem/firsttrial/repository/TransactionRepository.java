package com.kakaopay.bankingsystem.firsttrial.repository;

import com.kakaopay.bankingsystem.firsttrial.entity.Account;
import com.kakaopay.bankingsystem.firsttrial.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount(Account account);
}
