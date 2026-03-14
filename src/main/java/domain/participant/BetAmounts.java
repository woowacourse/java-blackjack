package domain.participant;

import domain.enums.Result;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BetAmounts {
    private final Map<String, BetAmount> betAmounts = new HashMap<>();

    public BetAmounts(List<String> names) {
        names.forEach(name -> betAmounts.put(name, new BetAmount(1000)));
    }

    public void addBetAmount(String name, int amount) {
        betAmounts.put(name, new BetAmount(amount));
    }

    public Map<String, Integer> calculateProfits(Map<String, Result> playerResults) {
        Map<String, Integer> profits = new LinkedHashMap<>();
        for (String name : playerResults.keySet()) {
            int profit = calculatePlayerProfit(playerResults.get(name), betAmounts.get(name));
            profits.put(name, profit);
        }
        return profits;
    }

    private int calculatePlayerProfit(Result result, BetAmount betAmount) {
        double profit = betAmount.amount() * result.getRate();
        return (int) profit;
    }
}
