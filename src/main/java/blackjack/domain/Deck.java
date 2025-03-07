package blackjack.domain;

import blackjack.common.ErrorMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class Deck {

    private static final int DECK_SIZE = 52;
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        validate(cards);
        this.cards = new ArrayList<>(shuffled(cards));
    }

    private void validate(List<Card> cards) {
        if (cards.size() != DECK_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DECK_SIZE.getMessage());
        }

        if (new HashSet<>(cards).size() != DECK_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.DUPLICATED_CARD_EXISTED.getMessage());
        }

    }

    private List<Card> shuffled(List<Card> cards) {
        ArrayList<Card> copiedCards = new ArrayList<>(cards);
        Collections.shuffle(copiedCards);

        return copiedCards;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public List<Card> takeCards(int size) {
        validateEmpty(size);

        return IntStream.range(0, size)
                .mapToObj(i -> cards.removeLast())
                .toList();
    }

    private void validateEmpty(int size) {
        if (cards.size() < size) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_DECK_SIZE.getMessage());
        }
    }
}
