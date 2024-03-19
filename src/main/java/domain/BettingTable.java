package domain;

import domain.constant.GamerResult;
import java.util.HashMap;
import java.util.Map;

public class BettingTable {
    private final Map<String, BettingAmount> bettingAmounts;

    public BettingTable(Map<String, BettingAmount> bettingAmounts) {
        this.bettingAmounts = new HashMap<>(bettingAmounts);
    }

    public Map<String, Double> getPlayersProfit(Map<String, GamerResult> totalResult) {
        validateSize(totalResult);
        Map<String, Double> playersProfit = new HashMap<>();
        totalResult.forEach((key, value) ->
                playersProfit.put(
                        key,
                        value.getGamerProfit(bettingAmounts.get(key).getAmount())
                ));
        return playersProfit;
    }

    private void validateSize(Map<String, GamerResult> totalResult) {
        if (totalResult.size() != bettingAmounts.size()) {
            throw new IllegalArgumentException("생성자로 입력받은 Map과 크기가 다릅니다");
        }
    }

    public double getDealerProfit(Map<String, GamerResult> totalResult) {
        return getPlayersProfit(totalResult).values().stream()
                .mapToDouble(Double::doubleValue).sum() * -1;
    }
}
