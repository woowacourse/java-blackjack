package domain.deck;

import domain.card.Card;
import java.util.Deque;

public class Deck {
    private final Deque<Card> unusedCards;

    public Deck(final Deque<Card> unusedCards) {
        this.unusedCards = unusedCards;
    }

    public Card pickCard() {
        return unusedCards.pop();
    }
}
