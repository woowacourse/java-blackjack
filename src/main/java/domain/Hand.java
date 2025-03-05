package domain;

import java.util.ArrayList;
import java.util.List;

public class Hand {

    private final List<TrumpCard> cards;

    public Hand(List<TrumpCard> cards) {
        validate(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validate(List<TrumpCard> cards) {
        validateNotNull(cards);
        validateDuplicate(cards);
    }

    private void validateNotNull(List<TrumpCard> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("손패는 카드를 가지고 있어야합니다.");
        }
    }

    private void validateDuplicate(List<TrumpCard> cards) {
        if (cards.stream().distinct().count() != cards.size()) {
            throw new IllegalArgumentException("손패에 중복된 카드가 있습니다.");
        }
    }

    public void addCard(TrumpCard card) {
        cards.add(card);
    }
}
