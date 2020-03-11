package domain;

import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class Deck {

    private static final int SIZE = 52;
    private static final String SIZE_ERROR_MESSAGE = "카드의 개수가 52개가 아닙니다.";
    private static final String DUPLICATION_ERROR_MESSAGE = "중복된 카드가 있습니다.";
    private Queue<Card> deck;

    public Deck(Queue<Card> deck) {
        validateSize(deck);
        validateDuplication(deck);
        this.deck = deck;
    }

    private void validateSize(Queue<Card> deck) {
        if (deck.size() != SIZE) {
            throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
        }
    }

    private void validateDuplication(Queue<Card> deck) {
        Set<Card> duplication = new HashSet<>(deck);

        if (deck.size() != duplication.size()) {
            throw new IllegalArgumentException(DUPLICATION_ERROR_MESSAGE);
        }
    }

}
