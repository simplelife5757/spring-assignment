package com.kakaopay.bankingsystem.secondtrial.controller;

import com.kakaopay.bankingsystem.secondtrial.domain.RoomAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/room-accounts")
@RequiredArgsConstructor
public class RoomAccountController {
    private final RoomAccountService roomAccountService;

    @PostMapping
    RoomAccountsCreateResponse createRoomAccounts(@RequestBody RoomAccountsCreateRequest request) {
        return new RoomAccountsCreateResponse(roomAccountService.createRoomAccounts(request));
    }

    @PutMapping
    Long withdrawRoomAccount(@RequestBody RoomAccountWithdrawRequest request) {
        return roomAccountService.withdrawRoomAccount(request);
    }
}
