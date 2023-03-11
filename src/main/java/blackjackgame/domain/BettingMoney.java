package blackjackgame.domain;

public class BettingMoney {
    private static final int MIN_BETTING_MONEY = 1000;
    private final int money;

    private BettingMoney(final int money) {
        isMoreThanThousand(money);
        this.money = money;
    }

    private void isMoreThanThousand(final int money) {
        if (money < MIN_BETTING_MONEY) {
            throw new IllegalArgumentException("베팅 금액은 최소 1000원 이상이어야 합니다.");
        }
    }

    public static BettingMoney of(final int money) {
        return new BettingMoney(money);
    }

    public int money() {
        return money;
    }
}
