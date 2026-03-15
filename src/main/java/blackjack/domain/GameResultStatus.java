package blackjack.domain;

import java.math.BigDecimal;

public enum GameResultStatus {

    BLACKJACK_WIN(new BigDecimal("1.5")),
    WIN(new BigDecimal("1.0")),
    DRAW(new BigDecimal("0.0")),
    LOSE(new BigDecimal("-1.0"));

    private final BigDecimal profit;

    GameResultStatus(BigDecimal profit) {
        this.profit = profit;
    }

    public BigDecimal calculateProfit(BigDecimal amount) {
        return amount.multiply(profit);
    }
}
