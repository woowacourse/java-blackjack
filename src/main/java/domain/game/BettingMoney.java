package domain.game;

public class BettingMoney {

    private final int amount;

    private BettingMoney(int amount) {
        this.amount = amount;
    }

    public BettingMoney onceHalf() {
        return new BettingMoney((int) (amount * 1.5));
    }

    public BettingMoney twice() {
        return new BettingMoney(amount * 2);
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof BettingMoney betting)) {
            return false;
        }

        return amount == betting.amount;
    }

    @Override
    public int hashCode() {
        return amount;
    }

    @Override
    public String toString() {
        return amount + "";
    }

    public static BettingMoney of(int amount) {
        if (amount < 1000) {
            throw new IllegalArgumentException("배팅금은 최소 1000원 이상입니다.");
        }
        if (amount > 1_000_000) {
            throw new IllegalArgumentException("배팅금은 100만원까지만 가능합니다.");
        }
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("배팅금은 1000원 단위로만 가능합니다.");
        }
        return new BettingMoney(amount);
    }
}
