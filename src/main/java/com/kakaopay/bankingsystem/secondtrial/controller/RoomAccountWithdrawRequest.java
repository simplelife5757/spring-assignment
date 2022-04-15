package com.kakaopay.bankingsystem.secondtrial.controller;


import lombok.Getter;

@Getter
public class RoomAccountWithdrawRequest {
    private String roomId;
    private long userId;
    private String token;
}
