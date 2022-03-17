package blackjack.domain;

public class BettingMoney {

    private final int value;

    private BettingMoney(final int value) {
        this.value = value;
    }

    public static BettingMoney of(final int value) {
        validateBettingMoney(value);
        return new BettingMoney(value);
    }

    private static void validateBettingMoney(final int value) {
        if (value <= 1000) {
            throw new IllegalArgumentException("배팅 금액은 1000보다 커야합니다.");
        }
    }

    public int getValue() {
        return value;
    }
}
