package model.money;

import java.util.Objects;

public class Money {
    private final int amount;

    Money(int money) {
        this.amount = money;
    }

    public static Money createBettingAmount(int money) {
        validate(money);
        return new Money(money);
    }

    private static void validate(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0보다 커야합니다.");
        }
    }

    public Money createMultiplyOf(int operand) {
        return new Money(amount * operand);
    }

    public Money createMultiplyOf(double operand) {
        return new Money((int) (amount * operand));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
