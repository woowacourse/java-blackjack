package blackjack.domain.user;

import blackjack.exception.ExceptionMessage;

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
}
