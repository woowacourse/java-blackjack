package blackjack.money;

public class BettingMoney {

    private static final String MINIMUM_BETTING_MONEY_EXCEPTION_MESSAGE = "배팅 최소 금액은 1000원입니다.";
    private static final int MINIMUM_BETTING_MONEY = 1000;

    private final int value;

    private BettingMoney(final int value) {
        this.value = value;
    }

    public static BettingMoney of(final int value) {
        validateBettingMoney(value);
        return new BettingMoney(value);
    }

    private static void validateBettingMoney(final int value) {
        if (value < MINIMUM_BETTING_MONEY) {
            throw new IllegalArgumentException(MINIMUM_BETTING_MONEY_EXCEPTION_MESSAGE);
        }
    }

    public int getValue() {
        return value;
    }
}
