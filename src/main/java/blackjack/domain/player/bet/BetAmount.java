package blackjack.domain.player.bet;

import blackjack.exception.NeedRetryException;
import java.util.Objects;

public final class BetAmount {

    private final int value;

    public BetAmount() {
        value = 0;
    }

    public BetAmount(final int value) {
        validatePositiveOrZero(value);
        this.value = value;
    }

    private void validatePositiveOrZero(final int value) {
        if (value < 0) {
            throw new NeedRetryException("배팅 금액은 0이상만 가능합니다.");
        }
    }

    public double multiply(final double leverage) {
        return leverage * value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof final BetAmount betAmount)) {
            return false;
        }
        return value == betAmount.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
