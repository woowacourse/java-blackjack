package domain;

import java.util.Objects;

public class BettingAmount {

    private static final int MINIMUM_BETTING_AMOUNT = 10_000;

    private final int amount;

    public BettingAmount(int amount) {
        validateRange(amount);
        this.amount = amount;
    }

    private void validateRange(int amount) {
        if (amount < MINIMUM_BETTING_AMOUNT) {
            throw new IllegalArgumentException("[ERROR] 베팅 금액은 최소 10,000원부터 입력 가능합니다.");
        }
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        BettingAmount that = (BettingAmount) object;
        return amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
