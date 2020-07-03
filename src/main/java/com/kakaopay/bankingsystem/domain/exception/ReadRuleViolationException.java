package com.kakaopay.bankingsystem.domain.exception;

public class ReadRuleViolationException extends IllegalArgumentException {
    public ReadRuleViolationException(String s) {
        super(s);
    }
}
