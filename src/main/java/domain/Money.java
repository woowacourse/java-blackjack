package domain;

import exception.ErrorMessage;

public class Money {
    private final long money;

    public Money(String moneyInput) {
        money = parseInput(moneyInput);
    }

    public Money(Long money) {
        this.money = money;
    }

    private long parseInput(String input) {
        try {
            long value = Integer.parseInt(input);
            validateNegative(value);
            return value;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MONEY.getMessage());
        }
    }

    private void validateNegative(long value) {
        if (value <= 0) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_MONEY.getMessage());
        }
    }

    public long getMoney() {
        return money;
    }
}
