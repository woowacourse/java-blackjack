package blackjack.domain.result;

import java.math.BigDecimal;

public enum ResultStatus {

    WIN(1.0),
    LOSE(-1.0),
    BLACKJACK(1.5),
    PUSH(0);

    private final double profitRate;

    ResultStatus(final double profitRate) {
        this.profitRate = profitRate;
    }

    public BigDecimal calculateProfit(final BigDecimal bettingAmount) {
        return bettingAmount.multiply(BigDecimal.valueOf(profitRate));
    }
}
