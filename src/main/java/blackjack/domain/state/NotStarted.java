package blackjack.domain.state;

import blackjack.domain.Hand;
import blackjack.domain.ResultType;
import blackjack.util.GameInitializer;

public class NotStarted implements State{
    @Override
    public State update(Hand hand) {
        if (hand.unwrap().size() == GameInitializer.STARTING_CARD_COUNT) {
            return StateFactory.getInstance(hand);
        }
        return this;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public State stay() {
        throw new IllegalStateException();
    }

    @Override
    public double profitRate(ResultType match) {
        throw new IllegalStateException();
    }
}
