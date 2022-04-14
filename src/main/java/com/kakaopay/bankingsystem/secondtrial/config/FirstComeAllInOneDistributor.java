package com.kakaopay.bankingsystem.secondtrial.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FirstComeAllInOneDistributor implements Distributor {
    @Override
    public List<Integer> distribute(int amount, int withdrawLimit) {
        List<Integer> result = Arrays.stream(new Integer[withdrawLimit]).map(integer -> 0).collect(Collectors.toList());
        result.set(0, amount);
        return result;
    }
}
