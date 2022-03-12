package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public class Cards {

    private static final int INITIAL_CARDS_SIZE = 2;

    private final List<Card> cards;

    private static final int BUST_STANDARD = 21;

    public Cards(List<Card> cards) {
        Objects.requireNonNull(cards, "[ERROR] 카드는 null일 수 없습니다.");
        cards = new ArrayList<>(cards);
        validate(cards);
        this.cards = cards;
    }

    private void validate(List<Card> cards) {
        validateSize(cards);
        validateDistinct(cards);
    }

    private void validateSize(List<Card> cards) {
        if (cards.size() != INITIAL_CARDS_SIZE) {
            throw new IllegalArgumentException("[ERROR] 카드를 두 장 받고 시작해야 합니다.");
        }
    }

    private void validateDistinct(List<Card> cards) {
        if (cards.stream().distinct().count() != cards.size()) {
            throw new IllegalArgumentException("[ERROR] 카드는 중복될 수 없습니다.");
        }
    }

    public void add(Card card) {
        cards.add(card);
    }

    public int calculateScore() {
        int minimumScore = calculateMinimumScore();
        if (containsAce()) {
            return IntStream.rangeClosed(0, 1)
                    .map(i -> minimumScore + (1 - i) * 10)
                    .filter(i -> i <= BUST_STANDARD)
                    .findFirst()
                    .orElse(minimumScore);
        }
        return minimumScore;
    }

    private int calculateMinimumScore() {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    private boolean containsAce() {
        return cards.stream()
                .anyMatch(card -> card.isSameValueWith(Denomination.ACE));
    }

    public List<Card> getCards() {
        return cards;
    }
}
