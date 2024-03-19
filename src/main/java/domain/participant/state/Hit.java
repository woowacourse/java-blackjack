package domain.participant.state;

import domain.card.Card;
import domain.card.Hand;

public class Hit extends Ready {
    public Hit(final Hand hand) {
        super(hand);
    }

    @Override
    public State draw(final Card card) {
        hand.add(card);
        if (hand.isBust()) {
            return new Bust(this.hand);
        }
        return new Hit(this.hand);
    }

    @Override
    public State stand() {
        return new Stand(this.hand);
    }

    @Override
    public boolean isHit() {
        return true;
    }
}
