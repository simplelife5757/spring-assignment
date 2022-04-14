package com.kakaopay.bankingsystem.firsttrial.repository;

import com.kakaopay.bankingsystem.firsttrial.entity.Account;
import com.kakaopay.bankingsystem.firsttrial.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByToken(Token token);
}
