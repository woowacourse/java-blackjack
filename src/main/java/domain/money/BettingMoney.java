package domain.money;

public class BettingMoney extends Money {
    private static final int MAX_SIZE = 10_000_000;

    private BettingMoney(final int value){
        super(value);
    }

    public static BettingMoney of(final int value){
        validateSize(value);
        return new BettingMoney(value);
    }

    public static void validateSize(final int value){
        if(value > MAX_SIZE){
            throw new IllegalArgumentException(String.format("베팅 금액은 %d원 이하여야합니다.", MAX_SIZE));
        }
    }
}
