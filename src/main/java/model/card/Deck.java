package model.card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck(List<Card> cards) {
        validate(cards);
        this.cards = new ArrayList<>(cards);
    }

    private static void validate(List<Card> cards) {
        validateNullAndEmpty(cards);
        validateDuplication(cards);
    }

    private static void validateNullAndEmpty(List<Card> cards) {
        if (cards == null || cards.isEmpty()) {
            throw new IllegalArgumentException("블랙잭 덱이 비어있습니다.");
        }
    }

    private static void validateDuplication(List<Card> cards) {
        if (cards.size() != new HashSet<>(cards).size()) {
            throw new IllegalArgumentException("블랙잭 덱은 중복된 카드가 존재할 수 없습니다.");
        }
    }

    public Card draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("추가 카드가 존재하지 않습니다.");
        }
        return cards.removeLast();
    }
}
