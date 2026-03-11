package domain;

import static domain.constant.GameRule.BET_AMOUNT_UNIT;
import static domain.constant.GameRule.MAX_BET_AMOUNT;
import static domain.constant.GameRule.MIN_BET_AMOUNT;
import static message.ErrorMessage.INVALID_BET_AMOUNT_RANGE;
import static message.ErrorMessage.INVALID_BET_AMOUNT_UNIT;

public record BetAmount(int amount) {
    public BetAmount {
        validateBetAmountRange(amount);
        validateBetAmountUnit(amount);
    }

    private void validateBetAmountUnit(int amount) {
        if (amount % BET_AMOUNT_UNIT != 0) {
            throw new IllegalArgumentException(INVALID_BET_AMOUNT_UNIT.getMessage());
        }
    }

    private void validateBetAmountRange(int amount) {
        if (amount < MIN_BET_AMOUNT || amount > MAX_BET_AMOUNT) {
            throw new IllegalArgumentException(INVALID_BET_AMOUNT_RANGE.getMessage());
        }
    }
}
