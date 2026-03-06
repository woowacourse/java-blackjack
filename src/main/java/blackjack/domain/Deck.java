package blackjack.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Deck {
    private static final int CARDS_COUNT = 52;

    private final List<TrumpCard> cards;

    public static Deck of(List<TrumpCard> cards) {
        return new Deck(cards);
    }

    public static Deck create(ShuffleStrategy strategy) {
        List<TrumpCard> cards = Arrays.stream(Suit.values())
                .flatMap(suit -> Arrays.stream(Rank.values())
                        .map(rank -> TrumpCard.of(suit, rank)))
                .collect(Collectors.toList());
        strategy.shuffle(cards);
        return new Deck(cards);
    }

    private Deck(List<TrumpCard> cards) {
        validate(cards);
        this.cards = new ArrayList<>(cards);
    }

    private void validate(List<TrumpCard> cards) {
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

    public List<TrumpCard> drawSecondTimes() {
        List<TrumpCard> cards = new ArrayList<>();

        cards.add(draw());
        cards.add(draw());
        return cards;
    }

    public TrumpCard draw() {
        if (cards.isEmpty()) {
            throw new IllegalArgumentException("덱에 카드가 없습니다.");
        }
        return cards.removeFirst();
    }
}
