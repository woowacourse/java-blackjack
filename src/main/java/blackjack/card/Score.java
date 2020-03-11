package blackjack.card;

import blackjack.card.exception.ScoreException;

import java.util.Objects;

public class Score {
    private final int score;

    public Score(int score) {
        validateRange(score);
        this.score = score;
    }

    private void validateRange(int score) {
        if (score < 0) {
            throw new ScoreException("점수는 양수이어야 합니다.");
        }
    }

    public Score add(Score other) {
        return new Score(this.score + other.score);
    }

    public boolean isUnder(int number) {
        return score < number;
    }

    public boolean isOver(int number) {
        return score > number;
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

    @Override
    public String toString() {
        return "Score{" +
                "score=" + score +
                '}';
    }
}
