package blackjack.model.gamer;

import blackjack.model.card.Card;
import blackjack.model.card.Hand;

public abstract class Gamer {

    private final Hand hand;

    public Gamer(final Hand hand) {
        this.hand = hand;
    }

    private void receiveCard(Card card) {
        hand.addCard(card);
    }
}
