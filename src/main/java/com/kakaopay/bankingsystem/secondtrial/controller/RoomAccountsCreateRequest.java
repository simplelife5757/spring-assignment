package com.kakaopay.bankingsystem.secondtrial.controller;

import lombok.Getter;

@Getter
public class RoomAccountsCreateRequest {
    private String roomId;
    private long ownerId;
    private int amount;
    private int withdrawLimit;
}
