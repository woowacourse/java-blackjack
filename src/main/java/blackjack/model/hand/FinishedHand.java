package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.Collection;

public abstract class FinishedHand extends Hand {

    protected FinishedHand(Collection<Card> cards) {
        super(cards);
    }

    @Override
    public boolean canHit() {
        return false;
    }
}
