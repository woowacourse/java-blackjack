package domain.card;

import java.util.Objects;

public class BlackJackScore {

    private static final int ADDITIONAL_ACE_VALUE = 10;
    private static final int BLACK_JACK_SCORE_VALUE = 21;

    public static final BlackJackScore BLACK_JACK_SCORE = BlackJackScore.of(BLACK_JACK_SCORE_VALUE);

    private final int value;

    private BlackJackScore(final int value) {
        validateNegative(value);
        this.value = value;
    }

    public static BlackJackScore of(final int value) {
        return new BlackJackScore(value);
    }

    private void validateNegative(final int value) {
        if (value < 0) {
            throw new IllegalArgumentException("점수는 음수일 수 없습니다.");
        }
    }

    public boolean isBlackJackScore() {
        return value == BLACK_JACK_SCORE_VALUE;
    }

    public boolean isBust() {
        return value > BLACK_JACK_SCORE_VALUE;
    }

    public boolean isLargerThan(final BlackJackScore other) {
        return value > other.value;
    }

    public BlackJackScore plusTenIfNotBust() {
        final BlackJackScore candidate = BlackJackScore.of(value + ADDITIONAL_ACE_VALUE);
        if (candidate.isBust()) {
            return this;
        }
        return candidate;
    }

    public int value() {
        return value;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof BlackJackScore)) return false;
        final BlackJackScore that = (BlackJackScore) o;
        return value == that.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
