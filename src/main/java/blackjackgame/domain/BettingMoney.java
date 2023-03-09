package blackjackgame.domain;

public class BettingMoney {
    private final int money;

    private BettingMoney(final int money) {
        this.money = money;
    }

    public static BettingMoney of(final int money) {
        return new BettingMoney(money);
    }

    public int getRevenue(final GameOutcome gameOutcome) {
        return gameOutcome.calculate(money);
    }
}
