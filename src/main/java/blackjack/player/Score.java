package blackjack.player;

public class Score {

    private static final int ADDITIONAL_ACE_SCORE = 10;
    private static final int BLACKJACK_SCORE = 21;

    private final int value;

    public Score(int value) {
        this.value = value;
    }

    public static Score zero() {
        return new Score(0);
    }

    public Score add(int other) {
        return new Score(value + other);
    }

    public Score add(Score other) {
        return new Score(value + other.value);
    }

    public Score addAceScoreOnSoftHand() {
        if (isSoftHandScore()) {
            return this.add(ADDITIONAL_ACE_SCORE);
        }
        return this;
    }

    private boolean isSoftHandScore() {
        return value + ADDITIONAL_ACE_SCORE <= BLACKJACK_SCORE;
    }

    public int toInt() {
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

        final Score score = (Score) o;

        return value == score.value;
    }

    @Override
    public int hashCode() {
        return value;
    }
}
