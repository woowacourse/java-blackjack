package blackjack.domain.card;

import java.util.Objects;

public class Score {

    private static final int BLACKJACK_SCORE = 21;
    private static final int MIN_SCORE = 5;

    private final int score;

    public Score(final int score) {
        checkMinScore(score);
        this.score = score;
    }

    private void checkMinScore(final int score) {
        if (score < MIN_SCORE) {
            throw new IllegalArgumentException("4이하의 점수는 존재하지 않습니다.");
        }
    }

    public static Score createMaxScore(final Cards cards) {
        return new Score(cards.maxScore());
    }

    public boolean isBlackjackScore() {
        return score == BLACKJACK_SCORE;
    }

    public boolean isBustScore() {
        return score > BLACKJACK_SCORE;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }
}
