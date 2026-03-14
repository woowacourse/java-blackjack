package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.Collection;

public abstract class FinishedHand extends Hand {

    protected FinishedHand(Collection<Card> cards) {
        super(cards);
    }

    @Override
    public final Hand hit(Card newCard) {
        throw new IllegalStateException("힛이 불가능한 상태입니다.");
    }

    @Override
    public boolean canHit() {
        return false;
    }
}
