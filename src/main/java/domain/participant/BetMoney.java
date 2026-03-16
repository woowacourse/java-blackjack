package domain.participant;

import java.math.BigDecimal;
import java.util.Objects;

public class BetMoney {
    public static final BetMoney ZERO = BetMoney.valueOf(BigDecimal.ZERO);

    private final BigDecimal value;

    private BetMoney(BigDecimal value) {
        this.value = value;
    }

    public static BetMoney valueOf(BigDecimal value) {
        return new BetMoney(value);
    }

    public static BetMoney valueOf(String value) {
        return new BetMoney(new BigDecimal(value));
    }

    public BetMoney multiply(double target) {
        return BetMoney.valueOf(value.multiply(BigDecimal.valueOf(target)));
    }

    public BetMoney sub(BetMoney target) {
        return BetMoney.valueOf(value.subtract(target.value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BetMoney betMoney = (BetMoney) o;
        return value.compareTo(betMoney.value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "BetMoney{" +
                "value=" + value +
                '}';
    }

    public BigDecimal getValue() {
        return value;
    }
}
