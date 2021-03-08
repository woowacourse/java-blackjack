package blackjack.domain.card;

import java.util.Deque;

public class Deck {

    private final Deque<Card> cards;

    public Deck(Deque<Card> cards) {
        this.cards = cards;
    }

    public Card draw() {
        return cards.pop();
    }
}
