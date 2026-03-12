package blackjack.domain;

import static blackjack.util.constant.ErrorMessage.AMOUNT_MINUS;
import static blackjack.util.constant.ErrorMessage.AMOUNT_ZERO;

public class BettingAmount {

    private final int amount;

    public BettingAmount(int amount) {
        validate(amount);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    private void validate(int amount) {
        minus(amount);
        zero(amount);
    }

    private void minus(int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(AMOUNT_MINUS);
        }
    }

    private void zero(int amount) {
        if (amount == 0) {
            throw new IllegalArgumentException(AMOUNT_ZERO);
        }
    }
}
