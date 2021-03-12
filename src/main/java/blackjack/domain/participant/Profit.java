package blackjack.domain.participant;

import java.util.Objects;

public class Profit {
    private final double profit;

    public Profit(Double profit) {
        validateNull(profit);
        this.profit = profit;
    }

    private void validateNull(Double profit) {
        Objects.requireNonNull(profit, "Null은 허용하지 않습니다.");
    }

    public Double getProfit() {
        return this.profit;
    }
}
