package domain.status;

import java.math.BigDecimal;

public class Bust implements EndStatus {

    @Override
    public BigDecimal profitWeight() {
        return BigDecimal.valueOf(0);
    }
}
