package com.kakaopay.bankingsystem.firsttrial.service;

import com.kakaopay.bankingsystem.firsttrial.entity.Token;
import com.kakaopay.bankingsystem.firsttrial.exception.TokenNotFoundException;
import com.kakaopay.bankingsystem.firsttrial.repository.TokenRepository;
import com.kakaopay.bankingsystem.firsttrial.utility.StringTokenGenerator;
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

    public Token generateToken(LocalDateTime expiredAt) {
        while (true) {
            String tokenName = StringTokenGenerator.generateToken();
            if (isUsableToken(tokenName, expiredAt)) {
                return new Token(tokenName, expiredAt);
            }
        }
    }

    private boolean isUsableToken(String newTokenName, LocalDateTime expiredAt) {
        List<Token> tokens = tokenRepository.findAllByTokenName(newTokenName);
        for (Token token: tokens) {
            if (token.isNotExpired(expiredAt)) {
                return false;
            }
        }
        return true;
    }
}
