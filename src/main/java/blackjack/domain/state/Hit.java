package blackjack.domain.state;

import blackjack.domain.participant.Hand;

public class Hit extends Running{

    public Hit() {
    }

    @Override
    public State check(Hand hand) {
        if (hand.isBust()) {
            return new Bust();
        }
        return new Hit();
    }

    @Override
    public State stay() {
        return new Stay();
    }
}
