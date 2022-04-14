package com.kakaopay.bankingsystem.secondtrial.domain;

import com.kakaopay.bankingsystem.firsttrial.entity.Token;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class RoomAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private RoomAccountToken token;
    private Long ownerId;
    private String roomId;
    private Integer amount;
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    public RoomAccount(RoomAccountToken token, Long ownerId, String roomId, Integer amount, LocalDateTime createdAt) {
        this.token = token;
        this.ownerId = ownerId;
        this.roomId = roomId;
        this.amount = amount;
        this.createdAt = createdAt;
    }
}
