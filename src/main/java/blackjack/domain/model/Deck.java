package blackjack.domain.model;

import java.util.List;

public class Deck {
    final List<Card> cards;

    public Deck(final List<Card> cards) {
        this.cards = cards;
    }
}
