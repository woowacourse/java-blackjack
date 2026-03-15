package domain.participant;

import static message.ErrorMessage.BET_AMOUNT_INVALID_UNIT;
import static message.ErrorMessage.BET_AMOUNT_OUT_OF_RANGE;

public record BetAmount(int amount) {
    public static final int MIN_BET_AMOUNT = 1_000;
    public static final int MAX_BET_AMOUNT = 300_000;
    public static final int BET_AMOUNT_UNIT = 1_000;

    public BetAmount {
        validateBetAmountRange(amount);
        validateBetAmountUnit(amount);
    }

    private void validateBetAmountUnit(int amount) {
        if (amount % BET_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException(BET_AMOUNT_INVALID_UNIT.getMessage());
        }
    }

    private void validateBetAmountRange(int amount) {
        if (amount < MIN_BET_AMOUNT || amount > MAX_BET_AMOUNT) {
            throw new IllegalArgumentException(BET_AMOUNT_OUT_OF_RANGE.getMessage());
        }
    }
}
