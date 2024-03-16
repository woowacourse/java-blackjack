package blackjack.model.cards;

import blackjack.model.blackjackgame.GameOutcomeStatus;
import java.util.Objects;

public final class Score {
    private static final int ACE_ADDITIONAL_SCORE = 10;
    private static final int BLACKJACK_SCORE = 21;

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public Score sum(final Score other) {
        return new Score(value + other.value);
    }

    public boolean isBlackJack() {
        return value == BLACKJACK_SCORE;
    }

    public GameOutcomeStatus getPlayerStatus(final Score other) {
        if (isBusted()) {
            return GameOutcomeStatus.LOSE;
        }
        if (other.isBusted()) {
            return GameOutcomeStatus.WIN;
        }
        return compare(other);
    }

    public boolean isBusted() {
        return value > BLACKJACK_SCORE;
    }

    public boolean isGreaterThan(final Score other) {
        return value > other.value;
    }

    public Score getScoreWhenHasAce() {
        if (value + ACE_ADDITIONAL_SCORE <= BLACKJACK_SCORE) {
            return new Score(value + ACE_ADDITIONAL_SCORE);
        }
        return this;
    }

    private GameOutcomeStatus compare(final Score other) {
        if (isGreaterThan(other)) {
            return GameOutcomeStatus.WIN;
        }
        if (other.isGreaterThan(this)) {
            return GameOutcomeStatus.LOSE;
        }
        return GameOutcomeStatus.PUSH;
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
