package blackjack.domain;

import java.util.Objects;

public class BettingMoney {

    private static final String NULL_USE_EXCEPTION_MESSAGE = "잘못된 인자 - Null 사용";
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
        if (Objects.isNull(input)) {
            throw new IllegalArgumentException(NULL_USE_EXCEPTION_MESSAGE);
        }
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
    public String toString() {
        return String.valueOf(bettingMoney);
    }
}
