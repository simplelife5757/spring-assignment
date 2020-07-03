package com.kakaopay.bankingsystem.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 3)
    private String tokenName;
    private LocalDateTime expiredAt;

    public Token(String tokenName, LocalDateTime expiredAt) {
        this.tokenName = tokenName;
        this.expiredAt = expiredAt;
    }

    public boolean isNotExpired(LocalDateTime expiredAt) {
        return expiredAt.isAfter(expiredAt);
    }
}
