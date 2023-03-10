package domain.status;

import java.math.BigDecimal;

public class Bust extends Status {

    public BigDecimal profitWeight() {
        return BigDecimal.valueOf(0);
    }
}
