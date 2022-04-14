package com.kakaopay.bankingsystem.secondtrial.domain;

import java.util.UUID;

public class UUIDStringTokenGenerator implements StringTokenGenerator{
    @Override
    public String generateStringToken() {
        return UUID.randomUUID().toString().substring(0, 3);
    }
}
