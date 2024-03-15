package blackjack.domain.participant;

import blackjack.exception.BettingAmountFormatException;
import blackjack.exception.BettingAmountNonPositiveException;

public class BetAmount {
    private static final int MIN_BETTING_AMOUNT = 1;

    private final int amount;

    public BetAmount(String amount) {
        int parsedAmount = parseInteger(amount);
        validateNonPositiveAmount(parsedAmount);
        this.amount = parsedAmount;
    }

    private int parseInteger(String amount) {
        try {
            return Integer.parseInt(amount);
        } catch(NumberFormatException e) {
            throw new BettingAmountFormatException();
        }
    }

    private void validateNonPositiveAmount(int amount) {
        if (amount < MIN_BETTING_AMOUNT) {
            throw new BettingAmountNonPositiveException();
        }
    }

    public int getAmount() {
        return amount;
    }
}
