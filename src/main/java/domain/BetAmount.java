package domain;

import java.util.Objects;

public class BetAmount {

    private final int amount;

    private BetAmount(final int amount) {
        this.amount = amount;
    }

    public static BetAmount from(int amount) {
        validatePositive(amount);
        return new BetAmount(amount);
    }

    public BetAmount multiply(double value) { //TODO 값 직접 바꾸기
        return new BetAmount((int) (amount * value));
    }

    public BetAmount makeZero() {
        return new BetAmount((-1) * amount);
    }

    public int getAmount() {
        return amount;
    }

    private static void validatePositive(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 배팅 금액입니다.");
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
}
