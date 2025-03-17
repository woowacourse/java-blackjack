package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Hand {

    private final List<TrumpCard> cards;

    public Hand(List<TrumpCard> cards) {
        validate(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validate(List<TrumpCard> cards) {
        validateNotNull(cards);
        validateNotDuplicate(cards);
    }

    private void validateNotNull(List<TrumpCard> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("손패는 카드를 가지고 있어야합니다.");
        }
    }

    private void validateNotDuplicate(List<TrumpCard> cards) {
        if (cards.stream().distinct().count() != cards.size()) {
            throw new IllegalArgumentException("손패에 중복된 카드가 있습니다.");
        }
    }

    public void addCard(TrumpCard card) {
        validateNotDuplicate(card);
        cards.add(card);
    }

    private void validateNotDuplicate(TrumpCard card) {
        if (cards.contains(card)) {
            throw new IllegalArgumentException("이미 손패에 있는 카드입니다.");
        }
    }

    public Score calculateScore() {
        return Score.from(cards);
    }

    public List<TrumpCard> getCards() {
        return List.copyOf(cards);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hand hand = (Hand) o;
        return Objects.equals(cards, hand.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
