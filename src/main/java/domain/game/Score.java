package domain.game;

import domain.card.Card;
import java.util.Set;

public record Score(int value) {
    private static final Score BLACKJACK_SCORE = new Score(21);
    private static final Score ADDITIONAL_ACE_SCORE = new Score(10);
    private static final Score DEALER_HIT_THRESHOLD = new Score(16);

    public static Score calculate(Set<Card> cards) {
        Score totalScore = calculateTotalScore(cards);
        if (hasAce(cards)) {
            return totalScore.withAce();
        }
        return totalScore;
    }

    private static Score calculateTotalScore(Set<Card> cards) {
        return new Score(cards.stream()
                .mapToInt(Card::score)
                .sum());
    }

    private static boolean hasAce(Set<Card> cards) {
        return cards.stream()
                .anyMatch(Card::isAce);
    }

    private Score withAce() {
        Score maximumScore = this.addScore(ADDITIONAL_ACE_SCORE);
        if (maximumScore.isGreaterThan(BLACKJACK_SCORE)) {
            return this;
        }
        return maximumScore;
    }

    public static boolean isBust(Set<Card> cards) {
        return calculate(cards).isGreaterThan(BLACKJACK_SCORE);
    }

    public static boolean isBlackJack(Set<Card> cards) {
        return calculate(cards).value() == BLACKJACK_SCORE.value();
    }

    public static boolean doesDealerNeedCard(Set<Card> cards) {
        return calculate(cards).value <= DEALER_HIT_THRESHOLD.value();
    }

    private Score addScore(Score other) {
        return new Score(this.value + other.value);
    }

    private boolean isGreaterThan(Score other) {
        return value > other.value;
    }
}
