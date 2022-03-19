package blackjack.domain.participant;

import java.math.BigDecimal;
import java.util.Objects;

public class BettingMoney {

    private final BigDecimal amount;

    private BettingMoney(BigDecimal bigDecimal) {
        this.amount = bigDecimal;
    }

    public BettingMoney(int amount) {
        this(BigDecimal.valueOf(amount));
    }

    public BettingMoney times(double percent) {
        BigDecimal multiplied = BigDecimal.valueOf(percent);
        BigDecimal result = amount.multiply(multiplied);
        return new BettingMoney(result);
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
