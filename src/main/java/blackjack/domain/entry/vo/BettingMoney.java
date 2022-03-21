package blackjack.domain.entry.vo;

import java.util.Objects;

public class BettingMoney {

    private static final int MINIMUM_AMOUNT_UNIT = 1000;
    private static final int MAXIMUM_AMOUNT = 5_000_000;

    private final int amount;

    public BettingMoney(int amount) {
        validateRange(amount);
        this.amount = amount;
    }

    public double profit(double earningRate) {
        return this.amount * earningRate;
    }

    private void validateRange(int amount) {
        if (Math.floorMod(amount, MINIMUM_AMOUNT_UNIT) != 0) {
            throw new IllegalArgumentException("배팅 금액은 1000단위어야 합니다.");
        }
        if (amount > MAXIMUM_AMOUNT) {
            throw new IllegalArgumentException("배팅 금액은 500만을 넘을 수 없습니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BettingMoney)) {
            return false;
        }
        BettingMoney that = (BettingMoney) o;
        return amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
