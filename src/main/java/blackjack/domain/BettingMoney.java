package blackjack.domain;

public class BettingMoney {

    private static final String LESS_THAN_MINIMUM_MONEY_ERROR_MESSAGE = "10미만의 금액은 입력할 수 없습니다.";
    private static final int MINIMUM_MONEY = 10;

    private final int amount;

    private BettingMoney(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public static BettingMoney of(int amount) {
        return new BettingMoney(amount);
    }

    private void validateAmount(int amount) {
        if (amount < MINIMUM_MONEY) {
            throw new IllegalArgumentException(LESS_THAN_MINIMUM_MONEY_ERROR_MESSAGE);
        }
    }

    public int getAmount() {
        return amount;
    }
}
