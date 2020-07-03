package com.kakaopay.bankingsystem.domain.repository;

import com.kakaopay.bankingsystem.domain.entity.Account;
import com.kakaopay.bankingsystem.domain.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount(Account account);
}
