package blackjackgame.domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck {
    private final Deque<Card> cards;

    public Deck() {
        this(initializeCards());
    }

    public Deck(final List<Card> cards) {
        this.cards = new ArrayDeque<>(cards);
    }

    private static List<Card> initializeCards() {
        List<Card> cards = new ArrayList<>();
        for (final Symbol symbol : Symbol.values()) {
            for (final CardValue cardValue : CardValue.values()) {
                cards.add(Card.of(symbol, cardValue));
            }
        }
        Collections.shuffle(cards);
        return cards;
    }

    public List<Card> firstPickCards() {
        List<Card> cards = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            cards.add(pickOne());
        }
        return cards;
    }

    public Card pickOne() {
        if (cards.peekFirst() == null) {
            cards.addAll(initializeCards());
        }
        return cards.removeFirst();
    }
}
