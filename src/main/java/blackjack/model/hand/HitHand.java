package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.Collection;

public class HitHand extends PlayingHand {

    public HitHand(Collection<Card> cards) {
        super(cards);
    }

    @Override
    public Hand hit(Card newCard) {
        if (isBustWith(newCard)) {
            return new BustHand(getNextCards(newCard));
        }

        return new HitHand(getNextCards(newCard));
    }

    @Override
    public double getEarningRate() {
        return 1;
    }
}
