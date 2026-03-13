package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.Collection;

public abstract class FinishedHand extends Hand {

    protected FinishedHand(Collection<Card> existsCards, Card newCard) {
        super(existsCards, newCard);
    }

    @Override
    public boolean canHit() {
        return false;
    }
}
