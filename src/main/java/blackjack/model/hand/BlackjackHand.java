package blackjack.model.hand;

import blackjack.model.card.Card;
import java.util.Collection;

public class BlackjackHand extends FinishedHand {

    public BlackjackHand(Collection<Card> existCards, Card newCard) {
        super(existCards, newCard);
    }

    @Override
    public double getEarningRate() {
        return 1.5;
    }

    @Override
    public Hand hit(Card newCard) {
        if (isBustWith(newCard)) {
            return new BustHand(cards, newCard);
        }

        return new BlackjackHand(cards, newCard);
    }
}
