package blackjack.domain.game;

import java.util.Objects;

public final class Score {

    private final int score;

    public Score(final int score) {
        this.score = score;
    }

    public Score minus(final Score score) {
        return new Score(this.score - score.score);
    }

    public boolean isMoreThen(final Score compareScore) {
        return this.score > compareScore.score;
    }

    public int getScore() {
        return score;
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
