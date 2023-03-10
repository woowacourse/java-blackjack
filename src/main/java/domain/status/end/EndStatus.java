package domain.status.end;

import domain.status.Status;
import java.math.BigDecimal;

public interface EndStatus extends Status {

    BigDecimal profitWeight();
}
