package domain.player;

import java.util.Objects;

public class BettingMoney {

    private static final int AMOUNT_UNIT = 1000;
    private static final int MINIMUM_BATTING_AMOUNT = 1000;

    private final int amount;

    private BettingMoney(final int amount) {
        validateZero(amount);
        validateThousandUnit(amount);
        this.amount = amount;
    }

    public static BettingMoney of(final int amount) {
        return new BettingMoney(amount);
    }

    private void validateZero(final int amount) {
        if (amount < MINIMUM_BATTING_AMOUNT) {
            throw new IllegalArgumentException("배팅 금액은 최소 " + MINIMUM_BATTING_AMOUNT + "원 이상입니다");
        }
    }

    private void validateThousandUnit(final int amount) {
        if (amount % AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException("배팅 금액은 " + AMOUNT_UNIT + "단위여야 합니다.");
        }
    }

    public int amount() {
        return amount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof BettingMoney)) return false;
        final BettingMoney that = (BettingMoney) o;
        return amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    public Revenue revenue(final GameResult blackjackWin) {
        return Revenue.of((int) (blackjackWin.revenueRate() * amount));
    }
}
