package blackjack.domain.money;

import java.math.BigDecimal;
import java.util.Objects;

public class Profits {
    private final BigDecimal profits;

    public Profits(BigDecimal profit) {
        this.profits = profit.setScale(0, BigDecimal.ROUND_DOWN);
    }

    public Profits multiply(BigDecimal finalRate) {
        return new Profits(profits.multiply(finalRate));
    }

    public Profits accumulate(Profits profits) {
        return new Profits(this.profits.add(profits.profits));
    }

    public BigDecimal getProfits() {
        return profits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profits profits1 = (Profits) o;
        return Objects.equals(profits, profits1.profits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profits);
    }
}
