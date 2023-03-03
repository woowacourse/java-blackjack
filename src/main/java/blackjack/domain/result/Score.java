package blackjack.domain.result;

import static blackjack.domain.result.Result.DRAW;
import static blackjack.domain.result.Result.LOSE;
import static blackjack.domain.result.Result.WIN;

public class Score implements Comparable<Score> {

    private final int value;

    public Score(final int value) {
        this.value = value;
    }

    public Result compare(final Score score) {
        final int result = this.compareTo(score);
        if (value > 21) {
            if (score.getValue() > 21) return DRAW;
            return LOSE;
        }

        if (result > 0) {
            return WIN;
        }
        if (result < 0) {
            return LOSE;
        }
        return DRAW;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compareTo(final Score score) {
        return Integer.compare(value, score.getValue());
    }
}
