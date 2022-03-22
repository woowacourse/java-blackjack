package blackjack.domain.user;

import java.util.Objects;

public class Money {
    private static final int MIN_MONEY_VALUE = 0;

    private final double amount;

    private Money(double amount) {
        validateNaturalNumber(amount);
        this.amount = amount;
    }

    private void validateNaturalNumber(double amount) {
        if (amount <= MIN_MONEY_VALUE) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 0보다 커야됩니다.");
        }
    }

    public static Money from(double amount) {
        return new Money(amount);
    }

    public double calculate(double value) {
        return value * amount;
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
