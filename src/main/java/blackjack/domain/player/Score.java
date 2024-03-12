package blackjack.domain.player;

import blackjack.domain.result.ResultStatus;

public record Score(int value) {
    public static Score from(final int value) {
        return new Score(value);
    }

    public Score add(final Score score) {
        return new Score(value + score.value);
    }

    public ResultStatus compare(final Score score) {
        if (this.value > score.value) {
            return ResultStatus.WIN;
        }
        if (this.value == score.value) {
            return ResultStatus.DRAW;
        }
        return ResultStatus.LOSE;
    }
}
