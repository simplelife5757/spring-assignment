package com.kakaopay.bankingsystem.domain.service;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class LuckyAccountCreateRequest {
    private Long userId;
    private String roomId;
    private long amount;
    private int withdrawLimit;
    private LocalDateTime requestAt;

    public LuckyAccountCreateRequest(Long userId, String roomId, long amount, int withdrawLimit, LocalDateTime requestAt) {
        this.userId = userId;
        this.roomId = roomId;
        this.amount = amount;
        this.withdrawLimit = withdrawLimit;
        this.requestAt = requestAt;
    }
}
