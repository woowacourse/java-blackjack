package blackjack.money;

import blackjack.domain.player.User;
import blackjack.domain.result.Result;
import java.util.Objects;

public class BettingMoney {

    private static final String MINIMUM_BETTING_MONEY_EXCEPTION_MESSAGE = "배팅 최소 금액은 1000원입니다.";
    private static final int MINIMUM_BETTING_MONEY = 1000;
    private static final double BLACKJACK_RATE = 1.5;
    private static final int MINUS = -1;

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

    public double calculateRevenue(final User user, final Result result) {
        if (result == Result.WIN && user.isBlackJack()) {
            return value * BLACKJACK_RATE;
        }
        if (result == Result.WIN) {
            return value;
        }
        if (result == Result.LOSE) {
            return value * MINUS;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BettingMoney that = (BettingMoney) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
