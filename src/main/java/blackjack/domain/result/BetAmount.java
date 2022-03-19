package blackjack.domain.result;

import java.util.Objects;

public final class BetAmount {
    private static final String BETTING_MIN_ERROR_MESSAGE = "1 이상의 정수를 입력해주세요.";

    private static final int MIN_ACE_COUNT = 0;

    private int value;

    public BetAmount(final int money) {
        this.value = money;
        validateBetting(value);
    }

    private void validateBetting(final int input) {
        if (input <= MIN_ACE_COUNT) {
            throw new IllegalArgumentException(BETTING_MIN_ERROR_MESSAGE);
        }
    }

    public int getMultipliedMoney(final double percentage) {
        value *= percentage;
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BetAmount point = (BetAmount) o;
        return value == point.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
