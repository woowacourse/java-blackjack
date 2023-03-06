package blackjack.domain;

public class Score {

    private static final int BLACK_JACK_NUMBER = 21;
    private static final int ACE_OFFSET = -10;

    private final int value;

    private Score(final int value) {
        this.value = value;
    }

    public static Score calculateScore(final CardGroup cardGroup) {
        int score = cardGroup.getTotalValue();
        int aceCount = cardGroup.getAceCount();

        while (score > BLACK_JACK_NUMBER && aceCount > 0) {
            score += ACE_OFFSET;
            aceCount--;
        }

        return new Score(score);
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
}
