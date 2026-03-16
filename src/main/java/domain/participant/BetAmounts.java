package domain.participant;

import domain.enums.Result;
import java.util.HashMap;
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

    public int calculatePlayerProfit(String name, Result result) {
        BetAmount betAmount = betAmounts.get(name);
        double profit = betAmount.amount() * result.getRate();
        return (int) profit;
    }
}
