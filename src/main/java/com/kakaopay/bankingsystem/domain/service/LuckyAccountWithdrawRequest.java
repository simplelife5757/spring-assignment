package com.kakaopay.bankingsystem.domain.service;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class LuckyAccountWithdrawRequest {
    private String tokenName;
    private String roomId;
    private Long userId;
    private LocalDateTime requestedAt;

    @Builder
    public LuckyAccountWithdrawRequest(String tokenName, String roomId, Long userId, LocalDateTime requestedAt) {
        this.tokenName = tokenName;
        this.roomId = roomId;
        this.userId = userId;
        this.requestedAt = requestedAt;
    }}
