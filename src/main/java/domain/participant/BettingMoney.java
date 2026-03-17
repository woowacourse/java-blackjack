package domain.participant;

public class BettingMoney {
    private final int money;
    private static final double BLACKJACK_PROFIT_RATE = 1.5;

    private BettingMoney(int money) {
        validate(money);
        this.money = money;
    }

    public static BettingMoney of(int money) {
        return new BettingMoney(money);
    }

    private void validate(int money) {
        if (money <= 0) {
            throw new IllegalArgumentException("배팅 금액은 0보다 커야 합니다.");
        }
    }

    public double winProfit() {
        return money;
    }

    public double loseProfit() {
        return -money;
    }

    public double blackjackProfit() {
        return money * BLACKJACK_PROFIT_RATE;
    }

    public int getMoney() {
        return money;
    }
}
