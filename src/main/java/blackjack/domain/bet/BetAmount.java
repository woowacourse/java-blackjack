package blackjack.domain.bet;

import java.util.Objects;

public final class BetAmount {

    private final int value;

    public BetAmount(int value) {
        validatePositiveOrZero(value);
        this.value = value;
    }

    private void validatePositiveOrZero(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("배팅 금액은 0이상만 가능합니다.");
        }
    }

    public double multiply(double leverage) {
        return leverage * value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BetAmount betAmount)) {
            return false;
        }
        return value == betAmount.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
