package blackjack.domain;

import blackjack.domain.card.Card;

import java.util.Collections;
import java.util.List;


public class Score {

    public static final Score bustLowerBound = new Score(22);
    public static final Score dealerHitUpperBound = new Score(17);
    public static final Score playerHitUpperBound = new Score(21);

    private static final int MAX_ACE_SCORE = 11;
    private static final int DIFFERENCE_WITH_ACE_NUMBER = 10;

    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score from(List<Card> cards) {
        int totalScore = getTotalScore(cards);

        if (totalScore <= MAX_ACE_SCORE && hasACE(cards)) {
            return new Score(totalScore + DIFFERENCE_WITH_ACE_NUMBER);
        }

        return new Score(totalScore);
    }

    private static boolean hasACE(List<Card> cards) {
        return cards.stream()
                .anyMatch(card -> card.isAce());
    }

    private static int getTotalScore(List<Card> cards) {
        return cards.stream()
                .mapToInt(card -> Collections.min(card.getScore()))
                .sum();
    }

    public boolean isLessThan(Score other) {
        return this.score < other.score;
    }

    public boolean isGreaterThan(Score other) {
        return this.score > other.score;
    }

    public int getScore() {
        return score;
    }
}
