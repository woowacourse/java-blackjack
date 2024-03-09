package blackjack.money;

public class BetMoney {

    private static final int BET_MONEY_UNIT = 1_000;

    private final int amount;

    private BetMoney(int amount) {
        validatePositiveAmount(amount);
        validateAmountDivisibleByUnit(amount);
        this.amount = amount;
    }

    public static BetMoney of(int amount) {
        return new BetMoney(amount);
    }

    private void validatePositiveAmount(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 양의 정수여야 합니다.");
        }
    }

    private void validateAmountDivisibleByUnit(int amount) {
        if (amount % BET_MONEY_UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 베팅은 1,000원 단위로만 가능합니다.");
        }
    }
}
