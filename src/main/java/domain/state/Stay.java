package domain.state;

import domain.MatchResult;
import domain.member.Hand;

public class Stay extends Finished {

    public Stay(Hand hand) {
        super(hand);
    }

    @Override
    public double earningRate(State dealerState) {
        if (dealerState.isBust()) {
            return MatchResult.WIN.profitRate();
        }
        if (dealerState.isBlackjack()) {
            return MatchResult.LOSE.profitRate();
        }
        return judgeScore(dealerState.hand().calculateTotalValue());
    }

    private double judgeScore(int dealerScore) {
        int myScore = hand.calculateTotalValue();
        if (myScore > dealerScore) {
            return MatchResult.WIN.profitRate();
        }
        if (myScore < dealerScore) {
            return MatchResult.LOSE.profitRate();
        }
        return MatchResult.DRAW.profitRate();
    }
}
