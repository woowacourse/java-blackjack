package blackjack.domain;

import blackjack.util.NullChecker;
import java.util.Objects;

public class BettingMoney {

    private static final String NOT_NUMBER_EXCEPTION_MESSAGE = "금액은 숫자여야 합니다.";
    private static final String POSITIVE_MINIMUM_UNIT_EXCEPTION_MESSAGE
        = "금액은 양수여야하며, 최소 단위는 10입니다.";

    private final int bettingMoney;

    private BettingMoney(int bettingMoney) {
        validateBettingMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    private void validateBettingMoney(int bettingMoney) {
        if (bettingMoney < 1 || bettingMoney % 10 != 0) {
            throw new IllegalArgumentException(POSITIVE_MINIMUM_UNIT_EXCEPTION_MESSAGE);
        }
    }

    public static BettingMoney of(String input) {
        validateInput(input);
        return new BettingMoney(Integer.parseInt(input));
    }

    private static void validateInput(String input) {
        NullChecker.validateNotNull(input);
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(NOT_NUMBER_EXCEPTION_MESSAGE);
        }
    }

    public int multiply(double ratio) {
        return (int) (bettingMoney * ratio);
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
        return bettingMoney == that.bettingMoney;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bettingMoney);
    }

    @Override
    public String toString() {
        return String.valueOf(bettingMoney);
    }
}
