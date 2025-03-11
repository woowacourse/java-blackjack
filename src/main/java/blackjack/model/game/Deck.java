package blackjack.model.game;

import blackjack.model.card.Card;

import java.util.List;

public class Deck {
    private final List<Card> cards;

    public Deck(final List<Card> cards) {
        this.cards = cards;
    }

    public int getCardCount() {
        return cards.size();
    }

    public Card drawCard() {
        return cards.removeFirst();
    }
}
