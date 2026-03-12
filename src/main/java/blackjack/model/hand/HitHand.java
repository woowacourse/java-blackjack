package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.Collection;

public class HitHand extends PlayingHand {

    public HitHand(Collection<Card> cards) {
        super(cards);
    }

    @Override
    public Hand nextState(Collection<Card> cards) {
        if (isBust(cards)) {
            return new BustHand(cards);
        }

        return new HitHand(cards);
    }
}
