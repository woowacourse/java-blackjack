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

    public Map<String, Integer> getTotalProfit(Map<String, GamerResult> totalResult) {
        Map<String, Integer> totalProfit = new HashMap<>();
        totalResult.forEach((key, value) ->
                totalProfit.put(
                        key,
                        bettingAmounts.get(key).getAmount() * value.getProfitRatio()
                ));
        totalProfit.put(GamerIdentifier.DEALER_IDENTIFIER, getDealerProfit(totalProfit));
        return totalProfit;
    }

    private int getDealerProfit(Map<String, Integer> totalProfit) {
        return totalProfit.values().stream()
                .mapToInt(Integer::intValue).sum() * -1;
    }
}
