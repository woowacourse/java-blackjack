package domain;

import domain.card.Card;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class Deck {
    private final Deque<Card> totalDeck;

    public Deck(List<Card> cards) {
        totalDeck = new ArrayDeque<>(cards);
    }

    public Card getNewCard() {
        return totalDeck.removeLast();
    }
}
