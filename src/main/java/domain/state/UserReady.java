package domain.state;

import domain.card.Card;
import domain.card.Hand;

public final class UserReady extends Ready {
    public UserReady() {
        super(new Hand());
    }

    public UserReady(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        final Hand newHand = getHand().add(card);

        if (getHand().isEmpty())
            return new UserReady(newHand);
        if (newHand.isBlackjack())
            return new Blackjack(newHand);
        return new UserHit(newHand);
    }
}
