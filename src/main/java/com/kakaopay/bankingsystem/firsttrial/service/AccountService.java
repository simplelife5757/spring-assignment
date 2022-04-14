package com.kakaopay.bankingsystem.firsttrial.service;

import com.kakaopay.bankingsystem.firsttrial.entity.Account;
import com.kakaopay.bankingsystem.firsttrial.entity.Token;
import com.kakaopay.bankingsystem.firsttrial.exception.AccountNotFoundException;
import com.kakaopay.bankingsystem.firsttrial.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {
   private final AccountRepository accountRepository;

   public Account create(AccountCreateRequest request) {
       Account account = Account.builder()
               .token(request.getToken())
               .ownerId(request.getOwnerId())
               .roomId(request.getRoomId())
               .createAt(request.getCreatedAt())
               .withdrawExpiredAt(request.getWithdrawExpireAt())
               .build();
       return accountRepository.save(account);
   }

   public Account findByToken(Token token) {
       return accountRepository.findByToken(token).orElseThrow(() -> new AccountNotFoundException("토큰=" + token));
   }
}

