package blackjack.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Deck {
    private static final int CARDS_COUNT = 52;

    private final List<TrumpCard> cards;

    private Deck(List<TrumpCard> cards) {
        validate(cards);
        this.cards = new ArrayList<>(cards);
    }

    public static Deck of(List<TrumpCard> cards) {
        return new Deck(cards);
    }

    public static Deck create(ShuffleStrategy strategy) {
        List<TrumpCard> cards = new ArrayList<>(TrumpCard.ALL_CARD);
        strategy.shuffle(cards);
        return new Deck(cards);
    }

    private void validate(List<TrumpCard> cards) {
        Objects.requireNonNull(cards, "덱 카드 목록은 null일 수 없습니다.");
        validateCardsCount(cards);
        validateDuplicates(cards);
    }

    private void validateCardsCount(List<TrumpCard> cards) {
        if (cards.size() != CARDS_COUNT) {
            throw new IllegalArgumentException("전체 카드 수는 52장이어야 합니다.");
        }
    }

    private void validateDuplicates(List<TrumpCard> cards) {
        long uniqueCardCount = cards.stream()
                .distinct()
                .count();

        if (uniqueCardCount != cards.size()) {
            throw new IllegalArgumentException("카드는 중복되면 안됩니다.");
        }
    }

    public TrumpCard deal() {
        if (cards.isEmpty()) {
            throw new IllegalStateException("덱에 카드가 없습니다.");
        }
        return cards.removeFirst();
    }

    public int countCards() {
        return cards.size();
    }
}
