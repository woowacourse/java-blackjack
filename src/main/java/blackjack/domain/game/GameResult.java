package blackjack.domain.game;

import java.math.BigDecimal;

public enum GameResult {

    WIN(BigDecimal.valueOf(1)),
    LOSE(BigDecimal.valueOf(-1)),
    DRAW(BigDecimal.valueOf(0)),
    BLACK_JACK(new BigDecimal("1.5"));

    private final BigDecimal times;

    GameResult(final BigDecimal times) {
        this.times = times;
    }

    public Money getProfit(Money money) {
        return money.multiple(times);
    }
}
