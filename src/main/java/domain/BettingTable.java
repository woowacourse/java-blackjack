package domain;

import domain.constant.GamerResult;
import java.util.HashMap;
import java.util.Map;

public class BettingTable {
    private final Map<String, Integer> bettingAmount;

    public BettingTable(Map<String, Integer> bettingAmount) {
        this.bettingAmount = new HashMap<>(bettingAmount);
    }

    public Map<String, Integer> getTotalProfit(Map<String, GamerResult> totalResult) {
        Map<String, Integer> totalProfit = new HashMap<>();
        totalResult.forEach((key, value) ->
                totalProfit.put(
                        key,
                        bettingAmount.get(key) * value.getProfitRatio()
                ));
        totalProfit.put("dealer", getDealerProfit(totalProfit));
        return totalProfit;
    }

    private int getDealerProfit(Map<String, Integer> totalProfit) {
        return totalProfit.values().stream()
                .mapToInt(Integer::intValue).sum() * -1;
    }
}
