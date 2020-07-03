package com.kakaopay.bankingsystem.domain.service;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LuckyAccountReadRequest {

    private String tokenName;
    private Long userId;

    public LuckyAccountReadRequest(String tokenName, Long userId) {
        this.tokenName = tokenName;
        this.userId = userId;
    }
}
