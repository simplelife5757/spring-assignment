package com.kakaopay.bankingsystem.firsttrial.utility;

import java.util.UUID;

public class StringTokenGenerator {
    public static String generateToken() {
        return UUID.randomUUID().toString().substring(0, 3);
    }
}
