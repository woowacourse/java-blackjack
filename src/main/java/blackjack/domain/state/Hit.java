package blackjack.domain.state;

import blackjack.domain.Hand;
import blackjack.domain.participant.Dealer;

public class Hit implements State {

    @Override
    public State update(Hand hand) {
        if (hand.isBust()) {
            return new Bust();
        }
        return this;
    }

    @Override
    public State stay() {
        return new Stay();
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profitRate(Dealer dealer, int score) {
        return 0;
    }
}
