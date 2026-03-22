package domain.betting;

import java.math.BigDecimal;
import java.util.Objects;

public class Revenue {
    private final BigDecimal money;

    public Revenue(String money) {
        this(parse(money));
    }

    public Revenue(int money) {
        this(BigDecimal.valueOf(money));
    }

    public Revenue(BigDecimal money) {
        this.money = money;
    }

    private static BigDecimal parse(String money) {
        try {
            return new BigDecimal(money);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("수익 값이 올바르지 않습니다.");
        }
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
