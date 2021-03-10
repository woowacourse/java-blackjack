package blackjack.domain.participant.state;

import blackjack.domain.Result;

public class Score {

    private static final int BLACKJACK = 21;
    private static final int TEN = 10;

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public Score reduceScoreIfBust() {
        if (this.isBust()) {
            return new Score(this.value - TEN);
        }
        return this;
    }

    public boolean isBust() {
        return this.value > BLACKJACK;
    }

    public boolean isBlackjack() {
        return this.value == BLACKJACK;
    }

    public Result compare(final Score score) {
        if (this.value > score.value) {
            return Result.WIN;
        }
        if (this.value < score.value) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

    public int getValue() {
        return this.value;
    }
}
