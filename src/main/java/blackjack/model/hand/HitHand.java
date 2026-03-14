package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.Collection;

public class HitHand extends PlayingHand {

    public HitHand(Collection<Card> existCards, Card newCard) {
        super(existCards, newCard);
    }

    @Override
    public Hand hit(Card newCard) {
        if (isBustWith(newCard)) {
            return new BustHand(cards, newCard);
        }

        return new HitHand(cards, newCard);
    }

    @Override
    public double getEarningRate() {
        return 1;
    }
}
