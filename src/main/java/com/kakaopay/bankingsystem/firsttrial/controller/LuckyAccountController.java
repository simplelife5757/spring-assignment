package com.kakaopay.bankingsystem.firsttrial.controller;

import com.kakaopay.bankingsystem.firsttrial.exception.LuckyAccountReadResponse;
import com.kakaopay.bankingsystem.firsttrial.service.LuckyAccountCreateRequest;
import com.kakaopay.bankingsystem.firsttrial.service.LuckyAccountReadRequest;
import com.kakaopay.bankingsystem.firsttrial.service.LuckyAccountService;
import com.kakaopay.bankingsystem.firsttrial.service.LuckyAccountWithdrawRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lucky-accounts")
@RequiredArgsConstructor
public class LuckyAccountController {

    private final LuckyAccountService accountService;

    @PostMapping
    public Long create(@RequestBody LuckyAccountCreateRequest request) {
        return accountService.create(request);
    }

    @PutMapping
    public void withdraw(@RequestBody LuckyAccountWithdrawRequest request) {
        accountService.withdraw(request);
    }

    @GetMapping
    public LuckyAccountReadResponse read(@RequestBody LuckyAccountReadRequest readRequest) {
        return accountService.read(readRequest);
    }
}
