package blackjack.domain.game;

import java.math.BigDecimal;

public class BettingMoney {
    private final BigDecimal value;

    public BettingMoney(int value) {
        this.value = BigDecimal.valueOf(value);
    }

    public int getValue() {
        return value.intValue();
    }
}
