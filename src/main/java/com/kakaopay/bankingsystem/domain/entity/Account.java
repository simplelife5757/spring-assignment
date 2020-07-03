package com.kakaopay.bankingsystem.domain.entity;

import lombok.Builder;
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
    @OneToOne
    private Token token;
    private Long ownerId;
    private String roomId;
    private LocalDateTime createAt;
    private LocalDateTime withdrawExpiredAt;

    @Builder
    private Account(Token token, Long ownerId, String roomId, LocalDateTime createAt, LocalDateTime withdrawExpiredAt) {
        this.token = token;
        this.ownerId = ownerId;
        this.roomId = roomId;
        this.createAt = createAt;
        this.withdrawExpiredAt = withdrawExpiredAt;
    }
}
