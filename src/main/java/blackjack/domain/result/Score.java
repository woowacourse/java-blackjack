package blackjack.domain.result;

public class Score {

    private static final int BLACK_JACK_NUMBER = 21;
    private static final int ACE_OFFSET = -10;

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public static Score calculateScore(int totalValue, int aceCount) {
        while (totalValue > BLACK_JACK_NUMBER && aceCount > 0) {
            totalValue += ACE_OFFSET;
            aceCount--;
        }
        return new Score(totalValue);
    }

    public int getValue() {
        return value;
    }

    public boolean isBust() {
        return value > BLACK_JACK_NUMBER;
    }

    public boolean isBlackJackScore() {
        return value == BLACK_JACK_NUMBER;
    }

    public boolean isBigger(final Score score) {
        return value > score.value;
    }

    public boolean isEqual(final Score score) {
        return value == score.value;
    }
}
