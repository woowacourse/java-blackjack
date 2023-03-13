package domain.state;

import domain.card.Card;
import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(final Card card) {
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return cards;
    }
}
