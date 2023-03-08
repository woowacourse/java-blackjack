package domain.player;

import java.util.Objects;

public class BettingMoney {

    private final double amount;

    private BettingMoney(final double amount) {
        this.amount = amount;
    }

    public static BettingMoney of(final double amount) {
        return new BettingMoney(amount);
    }

    public double amount() {
        return amount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof BettingMoney)) return false;
        final BettingMoney bettingMoney = (BettingMoney) o;
        return Double.compare(bettingMoney.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
