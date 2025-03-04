package blackjack;

import blackjack.common.ErrorMessage;
import java.util.HashSet;
import java.util.List;

public class Deck {
    private static final int DECK_SIZE = 52;
    private final List<Card> cards;

    public Deck(List<Card> cards) {
        validate(cards);
        this.cards = cards;
    }

    private void validate(List<Card> cards) {
        if (new HashSet<>(cards).size() != DECK_SIZE) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DECK_SIZE.getMessage());
        }

    }
}
