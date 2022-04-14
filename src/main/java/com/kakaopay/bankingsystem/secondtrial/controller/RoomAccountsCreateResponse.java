package com.kakaopay.bankingsystem.secondtrial.controller;

import lombok.Getter;

@Getter
public class RoomAccountsCreateResponse {
    private final String token;

    public RoomAccountsCreateResponse(String token) {
        this.token = token;
    }
}
