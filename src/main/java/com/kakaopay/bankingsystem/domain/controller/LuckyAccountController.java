package com.kakaopay.bankingsystem.domain.controller;

import com.kakaopay.bankingsystem.domain.exception.LuckyAccountReadResponse;
import com.kakaopay.bankingsystem.domain.service.LuckyAccountCreateRequest;
import com.kakaopay.bankingsystem.domain.service.LuckyAccountReadRequest;
import com.kakaopay.bankingsystem.domain.service.LuckyAccountService;
import com.kakaopay.bankingsystem.domain.service.LuckyAccountWithdrawRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController("/lucky-accounts")
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
