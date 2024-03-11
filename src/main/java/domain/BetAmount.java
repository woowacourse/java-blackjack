package domain;

import domain.score.Status;

public class BetAmount {

    private final int betAmount;

    public BetAmount(int betAmount) {
        this.betAmount = betAmount;
    }

    public BetAmount(String betAmount) {
        this.betAmount = Integer.parseInt(betAmount);
    }


    public int determineIncome(Status status) {
        if (status == Status.WIN) {
            return betAmount;
        }
        if (status == Status.LOSE) {
            return -betAmount;
        }
        return 0;
    }
}
