package model.result;

import java.math.BigDecimal;

public class ParticipantProfit {

    private final String name;
    private final BigDecimal profit;

    public ParticipantProfit(String name, BigDecimal profit) {
        this.name = name;
        this.profit = profit;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getProfit() {
        return profit;
    }
}
