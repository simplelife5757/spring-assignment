package com.kakaopay.bankingsystem.secondtrial.domain;


import com.kakaopay.bankingsystem.secondtrial.config.Distributor;
import com.kakaopay.bankingsystem.secondtrial.controller.RoomAccountsCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomAccountService {
    private final RoomAccountTokenService roomAccountTokenService;
    private final Distributor distributor;
    private final RoomAccountRepository roomAccountRepository;

    // Todo :
    public String createRoomAccounts(RoomAccountsCreateRequest request) {
        LocalDateTime createdAt = LocalDateTime.now();

        List<Integer> distributedAmounts = distribute(request.getAmount(), request.getWithdrawLimit());
        RoomAccountToken token = roomAccountTokenService.createToken(createdAt);

        List<RoomAccount> roomAccounts = distributedAmounts.stream()
                .map(amount -> new RoomAccount(token, request.getOwnerId(), request.getRoomId(), amount, createdAt))
                .collect(Collectors.toList());
        roomAccountRepository.saveAll(roomAccounts);
        return token.getToken();
    }

    private List<Integer> distribute(int amount, int withdrawLimit) {
        return distributor.distribute(amount, withdrawLimit);
    }
}
