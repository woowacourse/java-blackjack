package domain;

import java.util.Objects;

public class BetAmount {

    private final int amount;

    private BetAmount(final int amount) {
        this.amount = amount;
    }

    public static BetAmount from(int amount) {
        validatePositive(amount);
        validateMultiplesOfTen(amount);
        return new BetAmount(amount);
    }

    public BetAmount multiply(double value) { //TODO 값 직접 바꾸기
        return new BetAmount((int) (amount * value));
    }

    public BetAmount lose() {
        return new BetAmount((-1) * amount);
    }

    public BetAmount makeZero() {
        return new BetAmount(0);
    }

    public int getAmount() {
        return amount;
    }

    private static void validatePositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 배팅 금액은 양수만 입력가능합니다.");
        }
    }

    private static void validateMultiplesOfTen(int amount) {
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
