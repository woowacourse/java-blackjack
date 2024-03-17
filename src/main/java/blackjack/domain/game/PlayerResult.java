package blackjack.domain.game;

import java.util.function.Function;

public enum PlayerResult {
    BLACKJACK_WIN(betting -> (long) (betting * 1.5)),
    WIN(betting -> betting),
    PUSH(betting -> 0L),
    LOSE(betting -> betting * -1);

    private final Function<Long, Long> function;

    PlayerResult(Function<Long, Long> function) {
        this.function = function;
    }

    public Long additionalProfit(int betting) {
        return function.apply((long) betting);
    }

    public PlayerResult reverse() {
        if (this == WIN) {
            return LOSE;
        }
        if (this == LOSE) {
            return WIN;
        }
        return this;
    }
}
