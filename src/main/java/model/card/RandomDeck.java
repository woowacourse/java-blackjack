package model.card;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class RandomDeck implements Deck {
    private static final int DECK_SIZE = 52;

    private final Iterator<Card> cards;

    private RandomDeck(List<Card> cards) {
        validateDeckSize(cards);
        this.cards = List.copyOf(cards).iterator();
    }

    public static RandomDeck from(List<Card> cards) {
        List<Card> copied = List.copyOf(cards);

        return new RandomDeck(copied);
    }

    private static void validateDeckSize(List<Card> cards) {
        if (cards.size() != DECK_SIZE) {
            throw new IllegalArgumentException(
                    "덱은 " + DECK_SIZE + "개의 카드를 가져야 합니다(현재 " + cards.size() + "개).");
        }
    }

    @Override
    public Card draw() {
        try {
            return cards.next();
        } catch (NoSuchElementException e) {
            throw new IllegalStateException("덱이 비었습니다.", e);
        }
    }
}
