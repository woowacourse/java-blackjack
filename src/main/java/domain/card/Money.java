package domain.card;

import java.util.Objects;

public class Money {

    private final int amount;

    public Money(int amount) {
        validateUnderZero(amount);
        this.amount = amount;
    }

    private void validateUnderZero(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("베팅 금액은 0보다 커야 합니다.");
        }
    }

    public int calculateProfit(double earningRate) {
        return (int) (amount * earningRate);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Money money)) return false;
        return amount == money.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
