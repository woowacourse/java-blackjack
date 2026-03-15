package domain;

public class BettingMoney {
    private final int money;

    private BettingMoney(int money) {
        validate(money);
        this.money = money;
    }

    public static BettingMoney of(int money){
        return new BettingMoney(money);
    }

    private void validate(int money){
        if(money<=0){
            throw new IllegalArgumentException("배팅 금액은 0보다 커야 합니다.");
        }
    }

    public int getMoney(){
        return money;
    }
}
