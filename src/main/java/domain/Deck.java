package domain;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class Deck {
    private final Deque<Card> deck = new ArrayDeque<>();

    public Deck(final Cards cards) {
        List<Card> copiedCards = new ArrayList<>(cards.getCards());
        Collections.shuffle(copiedCards);
        copiedCards.forEach(deck::push);
    }

    public Deck(Deck otherDeck) {
        this.deck.addAll(new ArrayList<>(otherDeck.deck));
    }

    public Card draw() {
        validateEmpty();
        return deck.pop();
    }

    private void validateEmpty() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("카드가 없습니다.");
        }
    }
}
