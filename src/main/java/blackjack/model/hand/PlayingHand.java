package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.Collection;

public abstract class PlayingHand extends Hand {

    protected PlayingHand() {
        super();
    }

    protected PlayingHand(Collection<Card> existCards, Card newCard) {
        super(existCards, newCard);
    }

    @Override
    public boolean canHit() {
        return true;
    }
}
