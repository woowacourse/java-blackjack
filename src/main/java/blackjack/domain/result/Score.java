package blackjack.domain.result;

import java.util.Objects;

public class Score {
    private static final Score MIN = new Score(0);
    private static final Score BLACKJACK_SCORE = new Score(21);
    private static final Score ACE_GAP = new Score(10);

    private final int value;

    public Score(int score) {
        this.value = score;
    }

    public static Score min() {
        return Score.MIN;
    }

    public int getScore() {
        return value;
    }

    public boolean isNotOver(int maxValue) {
        return value <= maxValue;
    }

    public Score plusTenIfNotBust() {
        if (add(ACE_GAP).value <= BLACKJACK_SCORE.value) {
            return new Score(value + ACE_GAP.value);
        }
        return this;
    }

    public Score add(Score other) {
        return new Score(value + other.value);
    }

    public boolean isLessThan(Score other) {
        return this.value < other.value;
    }

    public boolean isBlackJack() {
        return this.equals(BLACKJACK_SCORE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score1 = (Score) o;
        return value == score1.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
