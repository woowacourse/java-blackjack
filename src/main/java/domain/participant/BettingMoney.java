package domain.participant;

import java.util.Objects;

public class BettingMoney {

    public static final int MIN_AMOUNT = 1000;
    public static final int MAX_AMOUNT = 100000;
    public static final int UNIT_AMOUNT = 1000;

    private final int amount;

    public BettingMoney(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        validatePositive(amount);
        validateRange(amount);
        validateMultiple(amount);
    }

    private void validatePositive(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("배팅 금액은 0 이상이어야 합니다.");
        }
    }

    private void validateRange(int amount) {
        if (amount < MIN_AMOUNT || amount > MAX_AMOUNT) {
            throw new IllegalArgumentException("배팅 금액은 최소 " + MIN_AMOUNT + "원 이상, 최대 " + MAX_AMOUNT + "원 이하여야 합니다.");
        }
    }

    private void validateMultiple(int amount) {
        if (amount % UNIT_AMOUNT != 0) {
            throw new IllegalArgumentException("배팅 금액은 " + UNIT_AMOUNT + "원 단위여야 합니다.");
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
