package blackjack.domain.user;

import blackjack.exception.ExceptionMessage;
import java.util.Objects;

public final class BettingAmount {

    private final int value;

    public BettingAmount(int value) {
        validateInvalidAmount(value);
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    private void validateInvalidAmount(int value) {
        if (value <= 0) {
            throw new IllegalArgumentException(ExceptionMessage.INVALID_BETTING_AMOUNT.getContent());
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        BettingAmount that = (BettingAmount) object;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
