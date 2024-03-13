package domain;

import java.util.Objects;

public class BetAmount {

    private final int amount;

    public BetAmount(int amount) {
        validatePositive(amount);
        this.amount = amount;
    }

    private void validatePositive(int amount) {
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
