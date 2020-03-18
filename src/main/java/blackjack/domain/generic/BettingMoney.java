package blackjack.domain.generic;

import java.util.Objects;

public final class BettingMoney {
    private static final int MINIMUM_MONEY = 0;
    private static final double BLACKJACK_WEIGHT = 1.5;
    private static final int LOSE_WEIGHT = -1;
    private final double money;

    private BettingMoney(double money) {
        this.money = money;
    }

    public static BettingMoney of(double money) {
        validate(money);
        return new BettingMoney(money);
    }

    private static void validate(double money) {
        if (money < MINIMUM_MONEY) {
            throw new IllegalArgumentException(String.format("베팅 금액은 %d원 이상이어야 합니다.", MINIMUM_MONEY));
        }
    }

    public BettingMoney getBlackjackMoney() {
        return new BettingMoney(this.money * BLACKJACK_WEIGHT);
    }

    public BettingMoney getWinMoney() {
        return new BettingMoney(money);
    }

    public BettingMoney getLoseMoney() {
        return new BettingMoney(money * LOSE_WEIGHT);
    }

    public BettingMoney multipleRate(double rate) {
        return new BettingMoney(this.money * rate);
    }

    public double getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BettingMoney that = (BettingMoney) o;
        return Double.compare(that.money, money) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(money);
    }

}
