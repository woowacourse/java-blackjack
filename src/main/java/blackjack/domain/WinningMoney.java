package blackjack.domain;

public class WinningMoney {
    private String name;
    private int money;

    private WinningMoney(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public static WinningMoney of(String name, int money) {
        return new WinningMoney(name, money);
    }

    public String name() {
        return name;
    }

    public int winningMoney() {
        return money;
    }
}
