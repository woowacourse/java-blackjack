package vo;

import java.math.BigDecimal;

public enum GameResult {
    BLACKJACK(new BigDecimal("1.5")),
    WIN(new BigDecimal("1.0")),
    LOSE(new BigDecimal("-1.0")),
    PUSH(new BigDecimal("0.0")),
    BUST(new BigDecimal("-1.0"));

    private final BigDecimal multiplier;

    GameResult(BigDecimal multiplier) {
        this.multiplier = multiplier;
    }

    public BigDecimal calculateProfit(BigDecimal betAmount) {
        return betAmount.multiply(multiplier);
    }
}
