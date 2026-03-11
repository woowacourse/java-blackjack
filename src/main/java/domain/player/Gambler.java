package domain.player;

import expcetion.BlackjackException;
import expcetion.ExceptionMessage;

public class Betting {

    private static final int MIN_BET = 1000;
    private static final int BET_UNIT = 1000;

    private final int amount;

    public Betting(int amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(int amount) {
        validateMinimum(amount);
        validateUnit(amount);
    }

    private void validateMinimum(int amount) {
        if (amount < MIN_BET) {
            throw new BlackjackException(ExceptionMessage.MIN_BET_AMOUNT_ERROR);
        }
    }

    private void validateUnit(int amount) {
        if (amount % BET_UNIT != 0) {
            throw new BlackjackException(ExceptionMessage.INVALID_BET_AMOUNT);
        }
    }

    public int amount() {
        return amount;
    }
}
