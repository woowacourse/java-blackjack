package blackjack.domain.result;

import java.math.BigDecimal;

public enum GameOutcome {
    BLACKJACK_WIN(new BigDecimal("1.5")),
    WIN(BigDecimal.ONE),
    DRAW(BigDecimal.ZERO),
    LOSE(BigDecimal.valueOf(-1)),
    ;

    private final BigDecimal payoutRate;

    GameOutcome(BigDecimal payoutRate) {
        this.payoutRate = payoutRate;
    }

    public BigDecimal getPayoutRate() {
        return payoutRate;
    }

}
