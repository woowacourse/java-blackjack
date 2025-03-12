package blackjack.gamer;

import blackjack.card.Card;
import blackjack.card.Hand;
import java.util.List;

public abstract class Gamer {

    private final Hand hand;

    public Gamer() {
        this.hand = new Hand();
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
