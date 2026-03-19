package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Profits {
    private final Map<Participant, Profit> profits;
    private final Profit dealerProfit;

    public Profits(Referee referee, Dealer dealer, Results results) {
        this.profits = new LinkedHashMap<>();
        int dealerProfit = 0;
        for (Entry<Player, Result> entry : results.getResults().entrySet()) {
            Profit profit = new Profit((int) referee.calculateProfit(entry.getValue(), entry.getKey().getBetAmount()));
            dealerProfit -= profit.getProfit();
            profits.put(entry.getKey(), profit);
        }
        this.dealerProfit = new Profit(dealerProfit);
    }

    public Map<Participant, Profit> getProfits() {
        return Collections.unmodifiableMap(profits);
    }

    public Profit getDealerProfit() {
        return dealerProfit;
    }
}
