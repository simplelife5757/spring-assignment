package com.kakaopay.bankingsystem.secondtrial.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class RoomAccountTokenService {
    private final StringTokenGenerator tokenGenerator;
    private final RoomAccountTokenRepository roomAccountTokenRepository;

    RoomAccountToken createStringToken(LocalDateTime roomAccountCreatedAt) {
        RoomAccountToken token = new RoomAccountToken(tokenGenerator.generateStringToken(), roomAccountCreatedAt);
        return roomAccountTokenRepository.save(token);
    }

    public RoomAccountToken findValidToken(String token) {
        RoomAccountToken roomAccountToken = roomAccountTokenRepository.findByToken(token);
        roomAccountToken.verify();
        return roomAccountToken;
    }
}
