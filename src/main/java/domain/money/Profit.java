package domain.money;

import domain.game.ResultStatus;
import java.util.Objects;

public class Profit {
    
    private final int base;
    
    private Profit(final int base) {
        this.base = base;
    }
    
    public static Profit from(Bet bet) {
        return new Profit(bet.getBet());
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.base);
    }
    
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        final Profit profit = (Profit) o;
        return this.base == profit.base;
    }
    
    public Profit calculateProfitFromBetAndResult(final ResultStatus resultStatus) {
        return new Profit((int) (this.base * resultStatus.getWeight()));
    }
}
