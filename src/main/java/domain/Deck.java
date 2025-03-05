package domain;

import java.util.List;

public class Deck {

    private final List<TrumpCard> cards;

    public Deck(List<TrumpCard> cards) {
        validate(cards);
        this.cards = cards;
    }

    private void validate(List<TrumpCard> cards) {
        validateSize(cards);
        validateDuplicate(cards);
    }

    private void validateSize(List<TrumpCard> cards) {
        if (cards.size() != 52) {
            throw new IllegalArgumentException();
        }
    }

    private void validateDuplicate(List<TrumpCard> cards) {
        if (cards.stream().distinct().count() != cards.size()) {
            throw new IllegalArgumentException();
        }
    }
}
