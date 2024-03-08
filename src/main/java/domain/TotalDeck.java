package domain;

import domain.card.Card;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class TotalDeck {
    private final Deque<Card> totalDeck;

    public TotalDeck(List<Card> cards) {
        totalDeck = new ArrayDeque<>();
        totalDeck.addAll(cards);
    }

    public Card getNewCard() {
        return totalDeck.removeLast();
    }

    public int size() {
        return totalDeck.size();
    }
}
