package blackjack.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class BattingMoney {

    private final BigDecimal amount;

    private BattingMoney(BigDecimal bigDecimal) {
        this.amount = bigDecimal;
    }

    public BattingMoney(String money) {
        this(new BigDecimal(money));
    }

    public BattingMoney add(BattingMoney money) {
        return new BattingMoney(amount.add(money.amount));
    }

    public BattingMoney times(double percent) {
        return new BattingMoney(amount.multiply(BigDecimal.valueOf(percent)));
    }

    public int getAmount() {
        return amount.intValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BattingMoney that = (BattingMoney) o;
        return Objects.equals(amount.intValue(), that.amount.intValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
