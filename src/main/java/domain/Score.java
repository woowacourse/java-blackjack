package domain;

public class Score {
    private static final int ACE_LOW = 1;
    private static final int ACE_HIGH = 11;
    private static final int BLACKJACK_CONDITION = 21;

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public Score add(final int score) {
        return new Score(value + score);
    }

    public Score addAce() {
        if (value + ACE_HIGH <= BLACKJACK_CONDITION) {
            return new Score(value + ACE_HIGH);
        }
        return new Score(value + ACE_LOW);
    }

    public int toInt() {
        return value;
    }
}
