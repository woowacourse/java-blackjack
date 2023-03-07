package domain.score;

import domain.player.DealerStatus;

public class Score {

    private static final int DEALER_HIT_BOUNDARY = 16;
    private static final int MAX_SCORE = 21;

    private final int value;

    private Score(final int value) {
        this.value = value;
    }

    public static Score from(final int value) {
        return new Score(value);
    }

    public DealerStatus compareScore(final Score score) {
        if (this.value > score.value) {
            return DealerStatus.WIN;
        }
        if (this.value == score.value) {
            return DealerStatus.DRAW;
        }
        return DealerStatus.LOSE;
    }

    public boolean isDealerHittable() {
        return value <= DEALER_HIT_BOUNDARY;
    }

    public boolean isBlackjackCandidate() {
        return value == MAX_SCORE;
    }

    public int getValue() {
        return value;
    }

    public boolean isBusted() {
        return value > MAX_SCORE;
    }
}
