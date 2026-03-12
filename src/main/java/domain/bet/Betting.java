package domain.bet;

import domain.state.Result;

public class Betting {
    private final Integer bettingAmount;

    public Betting(Integer bettingAmount) {
        this.bettingAmount = bettingAmount;
    }

    public Integer getProfit(Result result) {
        return result.getEarnCost(bettingAmount);
    }
}
