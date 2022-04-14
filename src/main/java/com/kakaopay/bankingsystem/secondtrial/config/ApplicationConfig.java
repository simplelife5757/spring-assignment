package com.kakaopay.bankingsystem.secondtrial.config;

import com.kakaopay.bankingsystem.secondtrial.domain.StringTokenGenerator;
import com.kakaopay.bankingsystem.secondtrial.domain.UUIDStringTokenGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
    @Bean
    public Distributor distributor() {
        return new FirstComeAllInOneDistributor();
    }

    @Bean
    public StringTokenGenerator tokenGenerator() {
        return new UUIDStringTokenGenerator();
    }
}
