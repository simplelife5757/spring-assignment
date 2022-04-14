package com.kakaopay.bankingsystem.secondtrial.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RoomAccountTokenService {
    private final StringTokenGenerator tokenGenerator;
    private final RoomAccountTokenRepository repository;

    RoomAccountToken createToken(LocalDateTime roomAccountCreatedAt) {
        RoomAccountToken token = new RoomAccountToken(tokenGenerator.generateStringToken(), roomAccountCreatedAt);
        return repository.save(token);
    }
}
