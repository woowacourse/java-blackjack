package blackjack.domain.card;

import blackjack.domain.Score;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cards {

    private static final int BUST_STANDARD = 21;

    private static final int INITIAL_CARDS_SIZE = 2;

    private final List<Card> cards;

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

    public Score calculateScore() {
        int score = calculateMinimumScore(cards);
        if (containsAce(cards) && score + 10 <= BUST_STANDARD) {
            return new Score(score + 10);
        }
        return new Score(score);
    }

    private int calculateMinimumScore(List<Card> cards) {
        return cards.stream()
                .mapToInt(Card::getValue)
                .sum();
    }

    private boolean containsAce(List<Card> cards) {
        return cards.stream()
                .anyMatch(card -> card.isSameValueWith(Denomination.ACE));
    }

    public List<Card> getCards() {
        return cards;
    }
}
