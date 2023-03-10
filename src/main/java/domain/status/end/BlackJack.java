package domain.status.end;

import java.math.BigDecimal;

public final class BlackJack extends EndStatus {

    @Override
    public BigDecimal profitWeight() {
        return BigDecimal.valueOf(1.5);
    }
}
