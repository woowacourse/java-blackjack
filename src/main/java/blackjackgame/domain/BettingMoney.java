package blackjackgame.domain;

public class BettingMoney {
    private final int money;

    private BettingMoney(int money) {
        this.money = money;
    }

    public static BettingMoney of(int money) {
        return new BettingMoney(money);
    }

    public int getRevenue(GameOutcome gameOutcome) {
        if (gameOutcome == GameOutcome.BLACKJACK_WIN) {
            return (int) (money * 1.5);
        }
        if (gameOutcome == GameOutcome.WIN) {
            return money;
        }
        if (gameOutcome == GameOutcome.LOSE) {
            return money * -1;
        }
        return 0;
    }
}
