package com.kakaopay.bankingsystem.secondtrial.domain;


import com.kakaopay.bankingsystem.secondtrial.config.Distributor;
import com.kakaopay.bankingsystem.secondtrial.controller.RoomAccountWithdrawRequest;
import com.kakaopay.bankingsystem.secondtrial.controller.RoomAccountsCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomAccountService {
    private final RoomAccountTokenService roomAccountTokenService;
    private final Distributor distributor;
    private final RoomAccountRepository roomAccountRepository;

    public String createRoomAccounts(RoomAccountsCreateRequest request) {
        LocalDateTime createdAt = LocalDateTime.now();

        List<Integer> distributedAmounts = distribute(request.getAmount(), request.getWithdrawLimit());
        RoomAccountToken roomAccountToken = roomAccountTokenService.createStringToken(createdAt);

        List<RoomAccount> roomAccounts = distributedAmounts.stream()
                .map(amount -> new RoomAccount(roomAccountToken, request.getOwnerId(), request.getRoomId(), amount, createdAt))
                .collect(Collectors.toList());
        roomAccountRepository.saveAll(roomAccounts);
        return roomAccountToken.getToken();
    }

    private List<Integer> distribute(int amount, int withdrawLimit) {
        return distributor.distribute(amount, withdrawLimit);
    }

    @Transactional
    public Long withdrawRoomAccount(RoomAccountWithdrawRequest request) {
        RoomAccountToken validRoomAccountToken = roomAccountTokenService.findValidToken(request.getToken());

        RoomAccount roomAccount = roomAccountRepository.findByToken(validRoomAccountToken);
        roomAccount.verify(request.getRoomId(), request.getUserId());
        roomAccount.updateUserId(request.getUserId());
        return roomAccount.getId();
    }
}
