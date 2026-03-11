package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.Collection;

public abstract class PlayingHand extends Hand {

    public PlayingHand() {
        super();
    }

    protected PlayingHand(Collection<Card> cards) {
        super(cards);
    }

    @Override
    public boolean canHit() {
        return true;
    }
}
