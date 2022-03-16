package blackjack.model.player.matcher;

import blackjack.model.player.Money;
import java.math.BigDecimal;

public enum ResultStatus {
    WIN, DRAW, LOSS, BLACKJACK;

    private static final BigDecimal BLACKJACK_PROFIT_RATE = new BigDecimal("1.5");

    public Money profit(Money money) {
        if (this == WIN) {
            return money;
        } else if (this == LOSS) {
            return money.negate();
        } else if (this == BLACKJACK) {
            return money.multiply(BLACKJACK_PROFIT_RATE);
        }
        return new Money(BigDecimal.ZERO);
    }
}
