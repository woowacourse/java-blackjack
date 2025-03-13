package domain;

import java.util.Objects;

public class BettingMoney {

    private final int amount;

    public BettingMoney(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        validatePositive(amount);
    }

    private void validatePositive(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("배팅 금액은 0 이상이어야 합니다.");
        }
    }

    public int calculateRevenueAmount(double multiple) {
        return (int) (amount * multiple);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BettingMoney that = (BettingMoney) o;

        return amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
