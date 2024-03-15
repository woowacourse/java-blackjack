package dto;

import java.math.BigDecimal;

public class DealerProfit {

    private final BigDecimal profit;

    public DealerProfit(){
        this.profit = BigDecimal.ZERO;
    }

    private DealerProfit(final BigDecimal profit) {
        this.profit = profit;
    }

    public DealerProfit accumulate(final BigDecimal profit){
        return new DealerProfit(this.profit.add(profit));
    }

    public BigDecimal getProfit() {
        return profit;
    }
}
