package blackjack.model.cards;

import blackjack.model.blackjackgame.PlayerResultStatus;
import java.util.Objects;

public final class Score {
    private static final int ACE_ADDITIONAL_SCORE = 10;
    private static final int BLACKJACK_SCORE = 21;

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public Score add(Score other) {
        return new Score(value + other.value);
    }

    public boolean isBlackJack() {
        return value == BLACKJACK_SCORE;
    }

    public PlayerResultStatus getPlayerStatus(Score other) {
        if (isBusted()) {
            return PlayerResultStatus.LOSE;
        }
        if (other.isBusted()) {
            return PlayerResultStatus.WIN;
        }
        return compare(other);
    }

    private PlayerResultStatus compare(Score other) {
        if (isGreaterThan(other)) {
            return PlayerResultStatus.WIN;
        }
        if (other.isGreaterThan(this)) {
            return PlayerResultStatus.LOSE;
        }
        return PlayerResultStatus.PUSH;
    }

    public boolean isBusted() {
        return value > BLACKJACK_SCORE;
    }

    public boolean isGreaterThan(Score other) {
        return value > other.value;
    }

    public Score getScoreWhenHasAce() {
        if (value + ACE_ADDITIONAL_SCORE <= BLACKJACK_SCORE) {
            return new Score(value + ACE_ADDITIONAL_SCORE);
        }
        return this;
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
