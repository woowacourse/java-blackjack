package blackjack.domain.participant;

import blackjack.domain.game.Result;

import java.util.Objects;

public class Score {

    private static final int BLACKJACK_SCORE_VALUE = 21;
    private static final int ACE_MINUS_SCORE_VALUE = 10;
    private static final int MINIMUM_SCORE_VALUE = 0;

    private final int value;

    public Score(int value) {
        if (value < MINIMUM_SCORE_VALUE) {
            throw new IllegalArgumentException("점수는 0보다 작을 수 없습니다.");
        }
        this.value = value;
    }

    public Score increase(Score other) {
        return new Score(this.value + other.value);
    }

    public Score calculateBestScoreAce(int aceCount) {
        if (this.value <= BLACKJACK_SCORE_VALUE) {
            return this;
        }
        int value = getBestScoreAce(aceCount);
        return new Score(value);
    }

    public boolean isGreaterThan(Score other) {
        return this.value > other.value;
    }

    public boolean isLessThan(Score other) {
        return this.value < other.value;
    }

    public Result calculateResultByScore(Score target) {
        if (this.isGreaterThan(target)) {
            return Result.WIN;
        }
        if (this.isLessThan(target)) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    private int getBestScoreAce(int aceCount) {
        int count = 0;
        int value = this.value;
        while (value > BLACKJACK_SCORE_VALUE && count++ < aceCount) {
            value -= ACE_MINUS_SCORE_VALUE;
        }
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public int getValue() {
        return value;
    }
}
