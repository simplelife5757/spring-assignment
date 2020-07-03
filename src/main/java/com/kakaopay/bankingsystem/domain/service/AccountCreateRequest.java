package com.kakaopay.bankingsystem.domain.service;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class AccountCreateRequest {
    private String token;
    private Long ownerId;
    private String roomId;
    private LocalDateTime createdAt;
    private LocalDateTime withdrawExpireAt;
    private LocalDateTime lookupExpireAt;

    @Builder
    private AccountCreateRequest(String token, Long ownerId, String roomId, LocalDateTime createdAt, LocalDateTime withdrawExpireAt, LocalDateTime lookupExpireAt) {
        this.token = token;
        this.ownerId = ownerId;
        this.roomId = roomId;
        this.createdAt = createdAt;
        this.withdrawExpireAt = withdrawExpireAt;
        this.lookupExpireAt = lookupExpireAt;
    }
}
