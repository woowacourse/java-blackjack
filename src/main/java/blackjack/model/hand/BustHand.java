package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.Collection;

public class BustHand extends FinishedHand {

    public BustHand(Collection<Card> existCards, Card newCard) {
        super(existCards, newCard);
    }

    @Override
    public Hand hit(Card newCard) {
        return new BustHand(cards, newCard);
    }
}
