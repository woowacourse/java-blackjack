package domain.money;

import domain.game.ResultStatus;
import java.util.Objects;

public class Profit {
    
    private final int profit;
    
    private Profit(final int profit) {
        this.profit = profit;
    }
    
    
    public static Profit create(final Bet bet, final ResultStatus status) {
        return new Profit((int) (bet.getBet() * status.getWeight()));
    }
    
    public Profit add(final Profit profit) {
        return new Profit(this.profit + profit.profit);
    }
    
    public Profit negate() {
        return new Profit(-this.profit);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(this.profit);
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
        return this.profit == profit.profit;
    }
    
    public int getProfit() {
        return this.profit;
    }
}
