package domain.participant;

import java.util.Objects;

public class Wager {
    private static final String MONEY_AMOUNT_CANNOT_NEGATIVE = "금액은 음수일 수 없습니다.";
    private final int amount;

    public Wager(int amount) {
        validateNonNegative(amount);
        this.amount = amount;
    }

    public int calculateProfit(double profitRate) {
        int resultAmount = (int)(amount * profitRate);
        return resultAmount - amount;
    }

    private void validateNonNegative(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(MONEY_AMOUNT_CANNOT_NEGATIVE);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Wager wager = (Wager) o;
        return amount == wager.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
