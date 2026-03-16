package domain.state;

import domain.MatchResult;
import domain.member.Hand;

public class Blackjack extends Finished {

    public Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public double earningRate(State dealerState) {
        if (dealerState.isBlackjack()) {
            return MatchResult.DRAW.profitRate();
        }
        return MatchResult.BLACKJACK_WIN.profitRate();
    }

    @Override
    public State stay() {
        return this;
    }
}
