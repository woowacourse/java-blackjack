package domain.money;

public class BettingMoney extends Money {
    private static final int MAX_SIZE = 10_000_000;
    private static final int MIN_SIZE = 100;

    private BettingMoney(final int value) {
        super(value);
    }

    public static BettingMoney of(final int value) {
        validateSize(value);
        return new BettingMoney(value);
    }

    public static BettingMoney of(final String valueInput) {
        validateNumeric(valueInput);
        int value = Integer.valueOf(valueInput);
        return BettingMoney.of(value);
    }

    public static void validateNumeric(String value) {
        try {
            Integer.valueOf(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자만 입력할 수 있습니다.");
        }
    }

    public static void validateSize(final int value) {
        if (value > MAX_SIZE) {
            throw new IllegalArgumentException(String.format("베팅 금액은 %d원 이하여야합니다.", MAX_SIZE));
        }
        if (value < MIN_SIZE) {
            throw new IllegalArgumentException(String.format("베팅 금액은 %d원 이상이어야합니다.", MIN_SIZE));
        }
    }
}
