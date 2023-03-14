package domain;

import java.util.Objects;

public class Money {
    private static final int MAX_AMOUNT = 100_000;
    private static final Money ZERO = new Money(0);

    private final int amount;

    public Money(int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    private void validateAmount(int amount) {
        if (amount >= MAX_AMOUNT) {
            throw new IllegalArgumentException("배팅 금액은 100,000 이하로 입력해야합니다.");
        }
    }

    public static Money zero() {
        return ZERO;
    }

    public Money add(Money other) {
        return new Money(this.amount + other.amount);
    }

    public Money subtract(Money other) {
        return new Money(this.amount - other.amount);
    }

    public Money multiply(double times) {
        return new Money((int) (this.amount * times));
    }

    public int amount() {
        return amount;
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
