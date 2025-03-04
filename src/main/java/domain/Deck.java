package domain;

import java.util.ArrayDeque;
import java.util.Queue;

public class Deck {
    private final Queue<Card> deck;

    public Deck(final Queue<Card> deck) {
        validateDuplicateCard(deck);
        this.deck = new ArrayDeque<>(deck);
    }

    private void validateDuplicateCard(final Queue<Card> deck) {
        if (deck.stream().distinct().count() != deck.size()) {
            throw new IllegalArgumentException("덱에는 중복된 카드가 들어올 수 없습니다!");
        }
    }

    public Card pickCard() {
        return deck.poll();
    }
}
