package domain.participant;

import domain.Profit;
import java.util.Objects;

public class BetAmount {

    private static final Profit LOSE_PROFIT = new Profit(0);

    private final int amount;

    public BetAmount(final int amount) {
        validatePositive(amount);
        validateMultiplesOfTen(amount);
        this.amount = amount;
    }

    public Profit keep() {
        return new Profit(amount);
    }

    public Profit multiply() {
        return new Profit((long) (1.5 * amount));
    }

    public Profit lose() {
        return new Profit((-1) * amount);
    }

    public Profit zero() {
        return LOSE_PROFIT;
    }

    private static void validatePositive(final int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 양수만 입력가능합니다.");
        }
    }

    private static void validateMultiplesOfTen(final int amount) {
        if (amount % 10 != 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 10의 배수만 입력가능합니다.");
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
        BetAmount betAmount = (BetAmount) o;
        return amount == betAmount.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    @Override
    public String toString() {
        return String.valueOf(amount);
    }
}
