package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Profits {
    private final List<Profit> profits;

    public Profits(Referee referee, Results results) {
        this.profits = new ArrayList<>();
        for (Map.Entry<Player, Result> entry : results.getResults().entrySet()) {
            Profit profit = new Profit((int) referee.calculateProfit(entry.getValue(), entry.getKey().getBetAmount()));
            profits.add(profit);
        }
    }

    public Profit getDealerProfit() {
        int dealerProfit = 0;
        for (Profit profit : profits) {
            dealerProfit -= profit.getProfit();
        }
        Profit profit = new Profit(dealerProfit);
        return profit;
    }

    public List<Profit> getProfits() {
        return profits;
    }
}
