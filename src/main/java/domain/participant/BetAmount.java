package domain.participant;

import domain.Profit;
import java.util.Objects;

public class BetAmount {

    private final int amount;

    private BetAmount(final int amount) {
        this.amount = amount;
    }

    public static BetAmount from(final int amount) {
        validatePositive(amount);
        validateMultiplesOfTen(amount);
        return new BetAmount(amount);
    }

    public Profit multiply(final double value) { //TODO 값 직접 바꾸기, 네이밍 변경
        return new Profit((int) (amount * value));
    }

    public Profit lose() {
        return new Profit((-1) * amount);
    }

    public Profit makeZero() {
        return new Profit(0);
    }

    public int getAmount() {
        return amount;
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
