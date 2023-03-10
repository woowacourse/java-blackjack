package domain.status;

import java.math.BigDecimal;

public class BlackJack implements EndStatus {

    @Override
    public BigDecimal profitWeight() {
        return BigDecimal.valueOf(1.5);
    }
}
