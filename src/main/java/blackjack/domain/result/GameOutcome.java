package blackjack.domain.result;

import java.math.BigDecimal;

public enum GameOutcome {
    BLACKJACK_WIN(new BigDecimal("1.5")),
    WIN(new BigDecimal("1")),
    DRAW(new BigDecimal("0")),
    LOSE(new BigDecimal("-1")),
    ;

    private final BigDecimal payoutRate;

    GameOutcome(BigDecimal payoutRate) {
        this.payoutRate = payoutRate;
    }

    public BigDecimal getPayoutRate() {
        return payoutRate;
    }

}
