package blackjack.domain.betting;

import java.util.Objects;

public class BettingAmount {
    private static final int MIN_AMOUNT = 1_000;
    private static final int MAX_AMOUNT = 1_000_000;
    private static final String INVALID_BETTING_AMOUNT = "베팅 금액은 %d원부터 %d원까지 입니다.";

    private final int amount;

    public BettingAmount(final int amount) {
        validateRange(amount);
        this.amount = amount;
    }

    private void validateRange(final int amount) {
        if (amount < MIN_AMOUNT || amount > MAX_AMOUNT) {
            throw new IllegalArgumentException(INVALID_BETTING_AMOUNT.formatted(MIN_AMOUNT, MAX_AMOUNT));
        }
    }

    public int multiply(final double winningRate) {
        return (int) (amount * winningRate);
    }

    public int subtractFrom(final int amount) {
        return amount - this.amount;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        BettingAmount that = (BettingAmount) object;
        return amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(amount);
    }
}
