package blackjack.domain.state;

import blackjack.domain.Hand;

public class Hit extends Running {

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
}
