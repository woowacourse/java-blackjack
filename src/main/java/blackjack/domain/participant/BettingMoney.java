package blackjack.domain.participant;

import java.math.BigDecimal;
import java.util.Objects;

public class BettingMoney {

    private final BigDecimal amount;

    private BettingMoney(BigDecimal bigDecimal) {
        this.amount = bigDecimal;
    }

    public BettingMoney(String input) {
        this(new BigDecimal(Integer.parseInt(input)));
    }

    public BettingMoney add(BettingMoney money) {
        return new BettingMoney(amount.add(money.amount));
    }

    public BettingMoney times(double percent) {
        return new BettingMoney(amount.multiply(BigDecimal.valueOf(percent)));
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
        BettingMoney that = (BettingMoney) o;
        return Objects.equals(amount.intValue(), that.amount.intValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
