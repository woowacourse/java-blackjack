package domain;

import java.util.Deque;
import java.util.LinkedList;

public class Hand {
    private final Deque<Card> cards;

    public Hand() {
        cards = new LinkedList<>();
    }

    public void add(Card card) {
        if (card.isAce()) {
            cards.addLast(card);
            return;
        }
        cards.addFirst(card);
    }

    public int calculateScore() {
        return 0;
    }
}
