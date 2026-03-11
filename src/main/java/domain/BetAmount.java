package domain;

import static domain.constant.GameRule.MAX_BET_AMOUNT;
import static domain.constant.GameRule.MIN_BET_AMOUNT;
import static message.ErrorMessage.INVALID_BET_AMOUNT_RANGE;

public class BetAmount {
    private final int amount;

    public BetAmount(int amount) {
        validateBetAmountRange(amount);
        this.amount = amount;
    }

    private void validateBetAmountRange(int amount) {
        if (amount < MIN_BET_AMOUNT || amount > MAX_BET_AMOUNT) {
            throw new IllegalArgumentException(INVALID_BET_AMOUNT_RANGE.getMessage());
        }
    }

    public int getAmount() {
        return amount;
    }
}
