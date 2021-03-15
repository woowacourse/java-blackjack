package blackjack.domain.card;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck {

    private final Deque<Card> cards;

    private Deck(final List<Card> cards) {
        this.cards = new ArrayDeque<>(cards);
    }

    public static Deck create() {
        List<Card> cards = Card.createDeck();
        Collections.shuffle(cards);
        return new Deck(cards);
    }

    public List<Card> popTwoCards() {
        return new ArrayList<>(
                Arrays.asList(cards.pop(), cards.pop())
        );
    }

    public Card popSingleCard() {
        return cards.pop();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }
}
