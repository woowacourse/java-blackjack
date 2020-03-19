package blackjack.domain.money;

import java.util.Map;

public class Profit {

    private double profit;

    public Profit(double profit) {
//        checkExists(profit);
        this.profit = profit;
    }

    public int getProfit() {
        return (int) profit;
    }
}
