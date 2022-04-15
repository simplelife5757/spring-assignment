package com.kakaopay.bankingsystem.secondtrial.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class RoomAccountToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private LocalDateTime roomAccountCreatedAt;
    private LocalDateTime usageExpiredAt;
    private LocalDateTime queryExpiredAt;

    public RoomAccountToken(String token, LocalDateTime roomAccountCreatedAt) {
        this.token = token;
        this.roomAccountCreatedAt = roomAccountCreatedAt;
        this.usageExpiredAt = roomAccountCreatedAt.plusMinutes(10);
        this.queryExpiredAt = roomAccountCreatedAt.plusDays(7);
    }

    public String getToken() {
        return token;
    }

    public void verify() {

    }
}
