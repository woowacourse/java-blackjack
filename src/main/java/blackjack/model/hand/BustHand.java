package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.Collection;

public class BustHand extends FinishedHand {

    public BustHand(Collection<Card> cards) {
        super(cards);
    }

    @Override
    protected Hand nextState(Collection<Card> cards) {
        return new BustHand(cards);
    }
}
