package com.kakaopay.bankingsystem.firsttrial.exception;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
public class LuckyAccountReadResponse {

    private LocalDateTime createdAt;
    private long totalAmount;
    private long withdrawCompletedTotalAmount;
    private Map<Long, Long> withdrawCompletedUserToAmount;

    public LuckyAccountReadResponse(LocalDateTime createdAt, long totalAmount, long withdrawCompletedTotalAmount, Map<Long, Long> withdrawCompletedUserToAmount) {
        this.createdAt = createdAt;
        this.totalAmount = totalAmount;
        this.withdrawCompletedTotalAmount = withdrawCompletedTotalAmount;
        this.withdrawCompletedUserToAmount = withdrawCompletedUserToAmount;
    }
}
