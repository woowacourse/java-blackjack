package blackjack.domain.game;

import java.math.BigDecimal;

public enum GameResult {

    WIN(new BigDecimal(1)),
    LOSE(new BigDecimal(-1)),
    DRAW(new BigDecimal(0)),
    BLACK_JACK(new BigDecimal("1.5"));

    private final BigDecimal times;

    GameResult(final BigDecimal times) {
        this.times = times;
    }

    public Money getProfit(Money money) {
        return money.multiple(times);
    }
}
