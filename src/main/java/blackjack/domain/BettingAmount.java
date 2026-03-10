package blackjack.domain;

import blackjack.exception.ErrorMessage;

public class BettingAmount {
    public static final int MIN_BETTING_AMOUNT = 1000;
    public static final int MAX_BETTING_AMOUNT = 50000;

    private int amount;

    public BettingAmount(int amount) {
        validateBettingAmountRange(amount);
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void calculateBlackjackProfit() {
        amount = (int) (amount * 1.5);
    }

    public void calculateLoseProfit() {
        amount = amount * (-1);
    }

    private void validateBettingAmountRange(int amount) {
        if (amount < MIN_BETTING_AMOUNT || amount > MAX_BETTING_AMOUNT) {
            throw new IllegalArgumentException(ErrorMessage.OUT_OF_AMOUNT_RANGE.getMessage());
        }
    }
}
