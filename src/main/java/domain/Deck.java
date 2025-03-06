package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {

    private final List<TrumpCard> cards;

    public Deck(List<TrumpCard> cards) {
        validate(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validate(List<TrumpCard> cards) {
        validateNotNull(cards);
        validateSize(cards);
        validateNotDuplicate(cards);
    }

    private void validateNotNull(List<TrumpCard> cards) {
        if (cards == null) {
            throw new IllegalArgumentException("덱은 카드를 가지고 있어야합니다.");
        }
    }

    private void validateSize(List<TrumpCard> cards) {
        if (cards.size() != 52) {
            throw new IllegalArgumentException("덱의 크기는 52여야 합니다.");
        }
    }

    private void validateNotDuplicate(List<TrumpCard> cards) {
        if (cards.stream().distinct().count() != cards.size()) {
            throw new IllegalArgumentException("덱에 중복된 카드가 있습니다.");
        }
    }

    public static Deck create() {
        List<TrumpCard> cards = initializeCards();

        return new Deck(cards);
    }

    private static ArrayList<TrumpCard> initializeCards() {
        return new ArrayList<>(Arrays.asList(TrumpCard.values()));
    }

    public TrumpCard draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱에 카드가 없습니다.");
        }

        return cards.removeLast();
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }
}
