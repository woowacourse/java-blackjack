package model.paticipant;

import model.judgement.BetAmount;
import model.judgement.Profit;
import model.judgement.ResultStatus;

public class BettingPlayer extends Participant implements Player {

    private static final int PLAYER_HIT_THRESHOLD = 21;

    private final BetAmount betAmount;

    public BettingPlayer(String name, int betAmount) {
        super(name);
        this.betAmount = new BetAmount(betAmount);
    }

    @Override
    public boolean canHit() {
        return calculateTotalScore() < PLAYER_HIT_THRESHOLD;
    }

    @Override
    public Profit calculateProfit(ResultStatus resultStatus) {
        return resultStatus.calculateProfit(betAmount);
    }
}
