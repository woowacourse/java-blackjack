package domain;

import java.util.Objects;

public class Score implements Comparable<Score> {

    private final int value;
    private final ScoreStatus scoreStatus;

    public Score(int value, ScoreStatus scoreStatus) {
        this.value = value;
        this.scoreStatus = scoreStatus;
    }

    public int getValue() {
        return value;
    }

    public ScoreStatus getScoreStatus() {
        return scoreStatus;
    }

    @Override
    public int compareTo(Score other) {
        if (this.scoreStatus != other.scoreStatus) {
            return this.scoreStatus.compareTo(other.scoreStatus);
        }
        if (this.scoreStatus == ScoreStatus.BUST || this.scoreStatus == ScoreStatus.BLACKJACK) {
            return 0;
        }
        return Integer.compare(value, other.value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Score score = (Score) o;
        return value == score.value && scoreStatus == score.scoreStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, scoreStatus);
    }
}
