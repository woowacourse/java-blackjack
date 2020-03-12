package blackjack.domain;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Objects;

public class Deck {
    private final LinkedList<Card> deck;

    public Deck(LinkedList<Card> cards) {
        this.deck = Objects.requireNonNull(cards);
        shuffle();
    }

    private void shuffle() {
        Collections.shuffle(deck);
    }

    public Card drawCard() {
        if (deck.isEmpty()) {
            throw new NullPointerException("덱이 비었습니다");
        }
        return deck.poll();
    }
}
