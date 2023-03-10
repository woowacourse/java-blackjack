package domain.money;

import java.util.Objects;

public class Profit {
    
    private final int profit;
    
    public Profit(int profit) {
        this.profit = profit;
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
        final Profit profit1 = (Profit) o;
        return this.profit == profit1.profit;
    }
}
