package blackjack.gamer;

import blackjack.card.Card;
import blackjack.card.Hand;
import java.util.List;

public abstract class Gamer {

    private final Hand hand;

    public Gamer(final Hand hand) {
        this.hand = hand;
    }

    private void receiveCard(Card card) {
        hand.addCard(card);
    }

    private List<Card> showAllCards() {
        return hand.openAllCards();
    }

    private int sumCards() {
        return hand.sumCards();
    }
}
