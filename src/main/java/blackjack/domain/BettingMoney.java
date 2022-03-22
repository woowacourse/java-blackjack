package blackjack.domain;

public class BettingMoney {

    private static final String LESS_THAN_MINIMUM_MONEY_ERROR_MESSAGE = "금액은 10원 단위로 입력해야 합니다.";
    private static final int BASED_UNIT_AMOUNT = 10;

    private final int amount;

    private BettingMoney(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public static BettingMoney of(int amount) {
        return new BettingMoney(amount);
    }

    private void validateAmount(int amount) {
        if (isInvalidUnitAmount(amount)) {
            throw new IllegalArgumentException(LESS_THAN_MINIMUM_MONEY_ERROR_MESSAGE);
        }
    }

    private boolean isInvalidUnitAmount(int amount) {
        return amount % BASED_UNIT_AMOUNT != 0;
    }

    public int getAmount() {
        return amount;
    }
}
