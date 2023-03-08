package domain;

import java.util.Objects;

public class Score {

    private static final Score BLACKJACK = new Score(21);
    private static final Score ACE_DECREASE = new Score(10);
    private static final int NONE_ACE_COUNT = 0;

    private final int value;

    private Score(int value) {
        this.value = value;
    }

    public static Score from(int value) {
        return new Score(value);
    }

    public Score add(Score other) {
        return new Score(this.getValue() + other.getValue());
    }

    public boolean isLessThanOrEqual(Score compareTarget) {
        return this.getValue() <= compareTarget.getValue();
    }

    public Score decreaseScoreByAce(Score limit, int aceCount) {
        Score score = this;
        while (isScoreDecreasableByAce(limit, aceCount)) {
            score = score.minus(ACE_DECREASE);
            aceCount--;
        }

        return score;
    }

    private boolean isScoreDecreasableByAce(Score limit, int aceCount) {
        return isNotBlackJack() && this.isGreaterThan(limit) && NONE_ACE_COUNT < aceCount;
    }

    private boolean isNotBlackJack() {
        return !this.equals(BLACKJACK);
    }

    public boolean isGreaterThan(Score compareTarget) {
        return this.getValue() > compareTarget.getValue();
    }

    private Score minus(Score other) {
        return new Score(this.getValue() - other.getValue());
    }

    public boolean isBust() {
        return this.isGreaterThan(BLACKJACK);
    }

    public int getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

}
