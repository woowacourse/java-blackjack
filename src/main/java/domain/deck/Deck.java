package domain.deck;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Deck {

    private static final int SIZE = 52;
    private static final String SIZE_ERROR_MESSAGE = "카드의 개수가 52개가 아닙니다.";
    private static final String DUPLICATION_ERROR_MESSAGE = "중복된 카드가 있습니다.";

    private Queue<Card> deck;

    public Deck(List<Card> cards) {
        validateSize(cards);
        validateDuplication(cards);
        this.deck = shuffle(cards);
    }

    private Queue<Card> shuffle(List<Card> cards) {
        Collections.shuffle(cards);
        return new LinkedList<>(cards);
    }

    private void validateSize(List<Card> deck) {
        if (deck.size() != SIZE) {
            throw new IllegalArgumentException(SIZE_ERROR_MESSAGE);
        }
    }

    private void validateDuplication(List<Card> deck) {
        Set<Card> duplication = new HashSet<>(deck);

        if (deck.size() != duplication.size()) {
            throw new IllegalArgumentException(DUPLICATION_ERROR_MESSAGE);
        }
    }

    public Card dealOut() {
        return deck.poll();
    }
}
