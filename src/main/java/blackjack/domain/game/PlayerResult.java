package blackjack.domain.game;

import java.util.function.Function;

public enum PlayerResult {
    BLACKJACK_WIN(betting -> (long) (betting * 1.5)),
    WIN(betting -> betting),
    PUSH(betting -> 0L),
    LOSE(betting -> betting * -1L);

    private final Function<Long, Long> function;

    PlayerResult(Function<Long, Long> function) {
        this.function = function;
    }

    public Long calculateProfit(Long betting) {
        return function.apply(betting);
    }
}
