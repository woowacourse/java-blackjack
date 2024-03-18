package domain.result;

import domain.participant.BetAmount;

public class IncomeCalculator {

    public Income determineIncome(Status status, BetAmount betAmount) {
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
