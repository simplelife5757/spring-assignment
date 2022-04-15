package com.kakaopay.bankingsystem.secondtrial.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface RoomAccountRepository extends JpaRepository<RoomAccount, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    RoomAccount findByToken(RoomAccountToken token);
}
