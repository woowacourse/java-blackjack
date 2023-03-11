package domain.card;

import domain.message.ExceptionMessage;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class CardDeck {

    private static final int SIZE_OF_CARD_DECK = 52;

    private final Deque<Card> cards;

    private CardDeck(final Deque<Card> cards) {
        validate(cards);
        this.cards = cards;
    }

    public static CardDeck createShuffled(final List<Card> inputCards) {
        Collections.shuffle(inputCards);
        Deque<Card> cards = new ArrayDeque<>(inputCards);
        return new CardDeck(cards);
    }

    private void validate(final Deque<Card> cards) {
        if (cards.size() != SIZE_OF_CARD_DECK) {
            throw new IllegalArgumentException(ExceptionMessage.CARD_DECK_INVALID_SIZE.getMessage());
        }

        if (isDuplicate(cards)) {
            throw new IllegalArgumentException(ExceptionMessage.CARD_DECK_DUPLICATED.getMessage());
        }
    }

    private boolean isDuplicate(final Deque<Card> cards) {
        return cards.stream()
                .distinct()
                .count() != SIZE_OF_CARD_DECK;
    }

    public Card draw() {
        return cards.remove();
    }
}
