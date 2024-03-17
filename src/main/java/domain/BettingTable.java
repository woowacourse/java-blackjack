package domain;

import domain.constant.GamerIdentifier;
import domain.constant.GamerResult;
import java.util.HashMap;
import java.util.Map;

public class BettingTable {
    private final Map<String, BettingAmount> bettingAmounts;

    public BettingTable(Map<String, BettingAmount> bettingAmounts) {
        this.bettingAmounts = new HashMap<>(bettingAmounts);
    }

    public Map<String, Double> getTotalProfit(Map<String, GamerResult> totalResult) {
        Map<String, Double> totalProfit = new HashMap<>();
        totalResult.forEach((key, value) ->
                totalProfit.put(
                        key,
                        bettingAmounts.get(key).getAmount() * value.getProfitRatio()
                ));
        totalProfit.put(GamerIdentifier.DEALER_IDENTIFIER, getDealerProfit(totalProfit));
        return totalProfit;
    }

    private double getDealerProfit(Map<String, Double> totalProfit) {
        return totalProfit.values().stream()
                .mapToDouble(Double::doubleValue).sum() * -1;
    }
}
