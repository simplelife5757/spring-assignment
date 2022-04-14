package com.kakaopay.bankingsystem.secondtrial.config;

import java.util.List;

public interface Distributor {
    public List<Integer> distribute(int amount, int withdrawLimit);
}
