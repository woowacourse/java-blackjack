package blackjack.model.cards;

import blackjack.model.blackjackgame.PlayerProfitCalculator;
import java.util.Objects;

public final class Score {
    private static final int ACE_ADDITIONAL_SCORE = 10;
    private static final int BLACKJACK_SCORE = 21;

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public Score add(final Score other) {
        return new Score(value + other.value);
    }

    public boolean isBlackJack() {
        return value == BLACKJACK_SCORE;
    }

    public PlayerProfitCalculator getPlayerStatus(final Score other) {
        if (isBusted()) {
            return PlayerProfitCalculator.LOSE;
        }
        if (other.isBusted()) {
            return PlayerProfitCalculator.WIN;
        }
        return compare(other);
    }

    private PlayerProfitCalculator compare(final Score other) {
        if (isGreaterThan(other)) {
            return PlayerProfitCalculator.WIN;
        }
        if (other.isGreaterThan(this)) {
            return PlayerProfitCalculator.LOSE;
        }
        return PlayerProfitCalculator.PUSH;
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
