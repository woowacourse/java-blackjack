package blackjack.model.player.matcher;

import java.math.BigDecimal;

public enum ResultStatus {
    WIN(BigDecimal.ONE), LOSS(BigDecimal.ONE.negate()),
    DRAW(BigDecimal.ZERO), BLACKJACK(new BigDecimal("1.5"));

    private BigDecimal profitRate;

    ResultStatus(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    Money profit(Money money) {
        return money.multiply(profitRate);
    }
}
