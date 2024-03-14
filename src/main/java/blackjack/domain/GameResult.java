package blackjack.domain;

import java.math.BigDecimal;
import java.util.function.BiFunction;

public enum GameResult {

    WIN((betting, isBlackjack) -> {
        if (isBlackjack) {
            return betting.multiply(BigDecimal.valueOf(1.5));
        }

        return betting;
    }),
    DRAW((betting, isBlackjack) -> BigDecimal.ZERO),
    LOSE((betting, isBlackjack) -> betting.multiply(BigDecimal.valueOf(-1))),
    ;

    private final BiFunction<BigDecimal, Boolean, BigDecimal> profit;

    GameResult(BiFunction<BigDecimal, Boolean, BigDecimal> profit) {
        this.profit = profit;
    }

    public BigDecimal getProfit(final BigDecimal betting, final boolean isBlackjack) {
        return profit.apply(betting, isBlackjack);
    }
}
