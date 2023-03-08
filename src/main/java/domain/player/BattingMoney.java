package domain.player;

import java.util.Objects;

public class BattingMoney {

    private final double amount;

    private BattingMoney(final double amount) {
        this.amount = amount;
    }

    public static BattingMoney of(final double amount) {
        return new BattingMoney(amount);
    }

    public double amount() {
        return amount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof BattingMoney)) return false;
        final BattingMoney battingMoney = (BattingMoney) o;
        return Double.compare(battingMoney.amount, amount) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
