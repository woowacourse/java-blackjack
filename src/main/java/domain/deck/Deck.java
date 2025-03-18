package domain.deck;

import domain.TrumpCard;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Deck {

    private final List<TrumpCard> cards;

    public Deck(List<TrumpCard> cards, ShuffleStrategy shuffleStrategy) {
        validate(cards, shuffleStrategy);
        cards = new ArrayList<>(cards);
        shuffleStrategy.shuffle(cards);
        this.cards = cards;
    }

    private void validate(List<TrumpCard> cards, ShuffleStrategy shuffleStrategy) {
        validateNotNull(cards, shuffleStrategy);
        validateSize(cards);
        validateNotDuplicate(cards);
    }

    private void validateNotNull(List<TrumpCard> cards, ShuffleStrategy shuffleStrategy) {
        if (cards == null || shuffleStrategy == null) {
            throw new IllegalArgumentException("덱은 카드와 섞기 전략 가지고 있어야합니다.");
        }
    }

    private void validateSize(List<TrumpCard> cards) {
        if (cards.size() != TrumpCard.TOTAL_COUNT) {
            throw new IllegalArgumentException("덱의 크기는 " + TrumpCard.TOTAL_COUNT + "여야 합니다.");
        }
    }

    private void validateNotDuplicate(List<TrumpCard> cards) {
        if (cards.stream().distinct().count() != cards.size()) {
            throw new IllegalArgumentException("덱에 중복된 카드가 있습니다.");
        }
    }

    public List<TrumpCard> drawMultiple(int count) {
        List<TrumpCard> cards = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            cards.add(draw());
        }

        return cards;
    }

    public TrumpCard draw() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱에 카드가 없습니다.");
        }

        return cards.removeLast();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deck deck = (Deck) o;
        return Objects.equals(cards, deck.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cards);
    }
}
