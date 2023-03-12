package domain.state;

import domain.card.Card;
import domain.card.Hand;

public final class UserHit extends Running {
    public UserHit(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        final Hand newHand = getHand().add(card);
        if(newHand.isBust())
            return new Bust(newHand);
        return new UserHit(getHand().add(card));
    }
}
