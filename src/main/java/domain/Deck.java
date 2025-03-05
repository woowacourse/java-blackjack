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
            throw new IllegalArgumentException("덱의 크기는 52여야 합니다.");
        }
    }

    private void validateDuplicate(List<TrumpCard> cards) {
        if (cards.stream().distinct().count() != cards.size()) {
            throw new IllegalArgumentException("덱에 중복된 카드가 있습니다.");
        }
    }

    public TrumpCard draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱에 카드가 없습니다.");
        }

        return cards.removeLast();
    }
}
