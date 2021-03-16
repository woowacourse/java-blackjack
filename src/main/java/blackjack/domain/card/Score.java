package blackjack.domain.card;

import java.util.Objects;

public class Score implements Comparable<Score> {
    private static final int BLACK_JACK = 21;
    private static final String EXCEPTION_CANNOT_BE_NEGATIVE_SCORE = "점수는 음수가 될 수 없어요";
    private final int score;

    private Score(int score) {
        if (score < 0) {
            throw new IllegalArgumentException(EXCEPTION_CANNOT_BE_NEGATIVE_SCORE);
        }
        this.score = score;
    }

    public static Score of(int score) {
        return new Score(score);
    }

    public Score add(int value) {
        return new Score(value + score);
    }

    public Score subtract(int value) {
        return new Score(score - value);
    }

    public boolean isBlackjack() {
        return score == BLACK_JACK;
    }

    public boolean isNotBlackjack() {
        return !isBlackjack();
    }

    public boolean isBurst() {
        return score > BLACK_JACK;
    }

    public boolean isNotBurst() {
        return !isBurst();
    }

    public boolean isHigherThan(Score counterpart) {
        return compareTo(counterpart) > 0;
    }

    public boolean isEqualAndLessThan(Score counterpart) {
        return !isHigherThan(counterpart);
    }

    public boolean isSameAs(Score counterpart) {
        return compareTo(counterpart) == 0;
    }

    @Override
    public int compareTo(Score o) {
        return Integer.compare(score, o.score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score1 = (Score) o;
        return score == score1.score;
    }

    @Override
    public int hashCode() {
        return Objects.hash(score);
    }

    @Override
    public String toString() {
        return String.valueOf(score);
    }

}
