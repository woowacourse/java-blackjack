package domain.player;

import exception.BlackjackException;
import exception.ExceptionMessage;

public record Betting(int amount) {

    private static final int MIN_BET = 1000;
    private static final int MAX_BET = 1_000_000;
    private static final int BET_UNIT = 1000;

    public Betting {
        validate(amount);
    }

    private void validate(int amount) {
        validateRange(amount);
        validateUnit(amount);
    }

    private void validateRange(int amount) {
        if (amount < MIN_BET || amount > MAX_BET) {
            throw new BlackjackException(ExceptionMessage.MIN_BET_AMOUNT_ERROR);
        }
    }

    private void validateUnit(int amount) {
        if (amount % BET_UNIT != 0) {
            throw new BlackjackException(ExceptionMessage.INVALID_BET_AMOUNT);
        }
    }
}