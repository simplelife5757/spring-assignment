package com.kakaopay.bankingsystem.domain.service;

import com.kakaopay.bankingsystem.domain.entity.Token;
import com.kakaopay.bankingsystem.domain.exception.TokenNotFoundException;
import com.kakaopay.bankingsystem.domain.exception.WithdrawFailureException;
import com.kakaopay.bankingsystem.domain.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenService {
    private final TokenRepository tokenRepository;

    public Token getValidToken(String tokenName) {
        List<Token> tokens = tokenRepository.findAllByTokenName(tokenName);
        for (Token token: tokens) {
            if (token.isNotExpired(LocalDateTime.now())) {
                return token;
            }
        }
        throw new TokenNotFoundException("토큰: " + tokenName);
    }

    public boolean isUsableToken(String newTokenName, LocalDateTime expiredAt) {
        List<Token> tokens = tokenRepository.findAllByTokenName(newTokenName);
        for (Token token: tokens) {
            if (token.isNotExpired(expiredAt)) {
                return false;
            }
        }
        return true;
    }
}
