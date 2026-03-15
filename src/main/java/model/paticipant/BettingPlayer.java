package model.paticipant;

import model.judgement.Profit;
import model.judgement.ResultStatus;

public class BettingPlayer extends Player {

    private final BetAmount betAmount;

    public BettingPlayer(String name, int betAmount) {
        super(name);
        this.betAmount = new BetAmount(betAmount);
    }

    public Profit calculateProfit(ResultStatus resultStatus) {
        return resultStatus.calculateProfit(betAmount);
    }
}
