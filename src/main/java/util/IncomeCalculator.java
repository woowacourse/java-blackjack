package util;

import domain.participant.BetAmount;
import domain.result.Income;
import domain.result.Status;

public class IncomeCalculator {

    private IncomeCalculator() {
    }

    public static Income determineIncome(Status status, BetAmount betAmount) {
        if (status == Status.WIN) {
            return new Income(betAmount.getValue());
        }
        if (status == Status.LOSE) {
            return new Income(-betAmount.getValue());
        }
        if (status == Status.WIN_BLACKJACK) {
            return new Income((int) Math.round(1.5 * betAmount.getValue()));
        }
        return new Income(0);
    }
}
