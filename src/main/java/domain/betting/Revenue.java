package domain.betting;

import java.math.BigDecimal;
import java.util.Objects;

public class Revenue {
    private final BigDecimal money;

    public Revenue(BigDecimal money) {
        this.money = money;
    }

    public BigDecimal getMoney() {
        return money;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Revenue revenue = (Revenue) o;
        return Objects.equals(money, revenue.money);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(money);
    }
}
