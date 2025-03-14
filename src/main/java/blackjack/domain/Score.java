package blackjack.domain;

import java.util.Set;

public class Score {
    private static final int BLACKJACK_GOAL_SCORE = 21;

    private final int value;

    public Score(Set<Integer> possibleScores) {
        this.value = calculateBestScore(possibleScores);
    }

    private int calculateBestScore(Set<Integer> possibleScores) {
        return possibleScores.stream()
                .filter(sum -> sum <= BLACKJACK_GOAL_SCORE)
                .max(Integer::compareTo)
                .orElse(choiceMinimumScore(possibleScores));
    }

    private int choiceMinimumScore(Set<Integer> possibleScores) {
        return possibleScores.stream()
                .min(Integer::compareTo)
                .orElse(0);
    }

    public boolean isUnderGoal() {
        return value <= BLACKJACK_GOAL_SCORE;
    }

    public boolean isBlackjack(int deckSize) {
        return value == BLACKJACK_GOAL_SCORE && deckSize == 2;
    }

    public boolean isBust() {
        return value > BLACKJACK_GOAL_SCORE;
    }

    public boolean isHigherThan(Score other) {
        return this.intValue() > other.intValue();
    }

    public int intValue() {
        return value;
    }
}
