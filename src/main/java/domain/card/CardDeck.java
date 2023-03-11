package domain.card;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class CardDeck {

    private static final int SIZE_OF_CARD_DECK = 52;

    private final Deque<Card> cards;

    private CardDeck(final List<Card> cards) {
        validate(cards);
        this.cards = new ArrayDeque<>(cards);
    }

    public static CardDeck createShuffled(List<Card> cards) {
        Collections.shuffle(cards);
        return new CardDeck(cards);
    }

    private void validate(final List<Card> cards) {
        if (cards.size() != SIZE_OF_CARD_DECK) {
            throw new IllegalArgumentException(String.format("전체 카드의 수는 %s장이어야 합니다.", SIZE_OF_CARD_DECK));
        }

        if (isDuplicate(cards)) {
            throw new IllegalArgumentException("중복된 카드가 존재합니다.");
        }
    }

    private boolean isDuplicate(final List<Card> cards) {
        return cards.stream()
                .distinct()
                .count() != cards.size();
    }

    public Card draw() {
        return cards.pop();
    }
}
