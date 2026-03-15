package domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class BettingResultCalculator {
    public static Map<Name, Integer> calculatePlayersProfit(TotalFinalResult totalFinalResult) {
        Map<Name, Integer> profits = new LinkedHashMap<>();
        for (FinalResult finalResult : totalFinalResult.getTotalResult()) {
            profits.put(finalResult.getName(), finalResult.getProfit());
        }
        return profits;
    }

    public static int calculateDealerProfit(Map<Name, Integer> playerProfits) {
        int dealerProfit = 0;
        for (Map.Entry<Name, Integer> entry : playerProfits.entrySet()) {
            dealerProfit += entry.getValue();
        }
        return -dealerProfit;
    }
}
