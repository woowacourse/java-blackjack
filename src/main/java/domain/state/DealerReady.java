package domain.state;

import domain.card.Card;
import domain.card.Hand;

public final class DealerReady extends Ready {
    public DealerReady() {
        super(new Hand());
    }

    public DealerReady(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        final Hand newHand = getHand().add(card);

        if (getHand().isEmpty())
            return new DealerReady(newHand);
        if (newHand.isBlackjack())
            return new Blackjack(newHand);
        return new DealerHit(newHand);
    }
}
