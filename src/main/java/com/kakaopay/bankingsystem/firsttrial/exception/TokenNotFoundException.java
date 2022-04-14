package com.kakaopay.bankingsystem.firsttrial.exception;

import javax.persistence.EntityNotFoundException;

public class TokenNotFoundException extends EntityNotFoundException {
    public TokenNotFoundException() {
    }

    public TokenNotFoundException(String message) {
        super("존재하지 않는 토큰입니다." + message);
    }
}
