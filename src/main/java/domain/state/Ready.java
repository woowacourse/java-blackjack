package domain.state;

import domain.Card;
import domain.Hand;
import domain.Score;

public class Ready implements State {
    private final Hand hand;

    public Ready(final Hand hand) {
        this.hand = hand;
    }

    @Override
    public State draw(final Card card) {
        hand.add(card);
        if (hand.score().equals(new Score(21))) {
            return new Blackjack(hand);
        }
        return new Hit(hand);
    }

    @Override
    public State stand() {
        return null;
    }
}
