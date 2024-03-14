package domain.state;

import domain.Card;
import domain.Hand;
import domain.Score;

public class Hit implements State {
    private final Hand hand;

    public Hit(final Hand hand) {
        this.hand = hand;
    }

    @Override
    public State hit(final Card card) {
        hand.add(card);
        if (hand.isBust()) {
            return new Bust(this.hand);
        }
        if (hand.score().equals(new Score(21))) {
            return new Blackjack(this.hand);
        }
        return new Hit(this.hand);
    }

    @Override
    public State stand() {
        return new Stand(this.hand);
    }
}
