package domain.player;

import java.util.Objects;

public class BetAmount {

    private static final int MIN_AMOUNT = 100;
    private static final int MAX_AMOUNT = 1_000_000;
    public static final BetAmount LOWEST = new BetAmount(MIN_AMOUNT);

    private final int value;

    public BetAmount(final int value) {
        validateRange(value);
        this.value = value;
    }

    private void validateRange(final int value) {
        if (value < MIN_AMOUNT) {
            throw new IllegalArgumentException(String.format("배팅 금액: %d, 배팅 금액은 최소 %d 이상입니다", value, MIN_AMOUNT));
        }
        if (value > MAX_AMOUNT) {
            throw new IllegalArgumentException(String.format("배팅 금액: %d, 배팅 금액은 최대 %d 이하입니다", value, MAX_AMOUNT));
        }
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final BetAmount betAmount = (BetAmount) o;
        return value == betAmount.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
