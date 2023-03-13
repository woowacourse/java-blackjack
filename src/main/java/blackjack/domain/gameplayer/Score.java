package blackjack.domain.gameplayer;

import blackjack.domain.card.Cards;

import java.util.Objects;

public final class Score {
    private static final int MAX_ACE_SCORE = 11;
    private static final int DIFFERENCE_WITH_ACE_NUMBER = 10;

    private final int score;

    private Score(int score) {
        this.score = score;
    }

    public static Score from(Cards cards) {
        int totalScore = getTotalScore(cards);

        if (totalScore <= MAX_ACE_SCORE && hasACE(cards)) {
            return new Score(totalScore + DIFFERENCE_WITH_ACE_NUMBER);
        }

        return new Score(totalScore);
    }

    public static Score of(int score) {
        return new Score(score);
    }

    private static boolean hasACE(Cards cards) {
        return cards.hasAce();
    }

    private static int getTotalScore(Cards cards) {
        return cards.calculateScore();
    }

    public boolean isLessThan(Score other) {
        return this.score < other.score;
    }

    public boolean isGreaterThan(Score other) {
        return this.score > other.score;
    }

    public boolean isEqualTo(Score other) {
        return this.score == other.score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
