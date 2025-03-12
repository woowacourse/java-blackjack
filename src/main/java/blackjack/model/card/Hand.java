package blackjack.model.card;

import java.util.List;

public class Hand {

    private final List<Card> cards;

    public Hand(final List<Card> cards) {
        this.cards = cards;
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public List<Card> openAllCards() {
        return cards;
    }
}
