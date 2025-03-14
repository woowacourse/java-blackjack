package domain.participant;

import java.util.Objects;

public class Money {
    private static final String MONEY_AMOUNT_CANNOT_NEGATIVE = "금액은 음수일 수 없습니다.";
    private final int amount;

    public Money(int amount) {
        validateNonNegative(amount);
        this.amount = amount;
    }

    public Money multiply(double factor) {
        return new Money((int) (amount * factor));
    }

    private void validateNonNegative(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(MONEY_AMOUNT_CANNOT_NEGATIVE);
        }
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
