package domain.status;

import java.math.BigDecimal;

public class BlackJack extends Status {
    public BigDecimal profitWeight() {
        return BigDecimal.valueOf(1.5);
    }
}
