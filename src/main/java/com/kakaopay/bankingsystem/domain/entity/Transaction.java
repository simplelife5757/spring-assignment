package com.kakaopay.bankingsystem.domain.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;
    @ManyToOne
    private Account account;
    private long amount;
    private Long userId;
    private LocalDateTime createdAt;

    @Builder(access = AccessLevel.PRIVATE)
    private Transaction(TransactionStatus status, Account account, long amount, Long userId, LocalDateTime createdAt) {
        this.status = status;
        this.account = account;
        this.amount = amount;
        this.userId = userId;
        this.createdAt = createdAt;
    }

    public static Transaction depositCompleted(Account account, long amount, Long userId, LocalDateTime createdAt) {
        return Transaction.builder()
                .status(TransactionStatus.DEPOSIT_COMPLETED)
                .account(account)
                .amount(amount)
                .userId(userId)
                .createdAt(createdAt)
                .build();
    }

    public static Transaction withdrawStandby(Account account, long amount, Long userId, LocalDateTime createdAt) {
        return Transaction.builder()
                .status(TransactionStatus.WITHDRAW_STANDBY)
                .account(account)
                .amount(amount)
                .userId(userId)
                .createdAt(createdAt)
                .build();
    }

    public static Transaction withdrawCompleted(Account account, long amount, Long userId, LocalDateTime createdAt) {
        return Transaction.builder()
                .status(TransactionStatus.WITHDRAW_COMPLETED)
                .account(account)
                .amount(amount)
                .userId(userId)
                .createdAt(createdAt)
                .build();
    }
}
