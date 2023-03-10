package domain.status.end;

import java.math.BigDecimal;

public final class Bust extends EndStatus {

    @Override
    public BigDecimal profitWeight() {
        return BigDecimal.valueOf(0);
    }
}
