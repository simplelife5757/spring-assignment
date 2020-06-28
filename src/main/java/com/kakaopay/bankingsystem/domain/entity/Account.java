package com.kakaopay.bankingsystem.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, length = 3)
    private String token;
    private Long ownerId;
    private String roomId;
    private LocalDateTime createAt;
    private LocalDateTime withdrawExpiredAt;
    private LocalDateTime lookupExpiredAt;

    public Account(String token, Long ownerId, String roomId, LocalDateTime createAt, LocalDateTime withdrawExpiredAt, LocalDateTime lookupExpiredAt) {
        this.token = token;
        this.ownerId = ownerId;
        this.roomId = roomId;
        this.createAt = createAt;
        this.withdrawExpiredAt = withdrawExpiredAt;
        this.lookupExpiredAt = lookupExpiredAt;
    }
}
