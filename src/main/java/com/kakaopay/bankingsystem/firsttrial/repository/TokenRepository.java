package com.kakaopay.bankingsystem.firsttrial.repository;

import com.kakaopay.bankingsystem.firsttrial.entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Long> {
    List<Token> findAllByTokenName(String token);
}
