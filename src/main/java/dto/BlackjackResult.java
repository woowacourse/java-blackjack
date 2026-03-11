package dto;

import java.util.LinkedHashMap;
import java.util.Map;

public record BlackjackResult(
        int dealerProfit,
        Map<String, Integer> matchResultLog
) {
    public static BlackjackResult from(Map<String, Integer> matchResultLog) {
        int totalGamblersProfit = matchResultLog.values().stream()
                .mapToInt(Integer::intValue)
                .sum();


        int dealerProfit = calculateDealerProfit(totalGamblersProfit);

        return new BlackjackResult(dealerProfit, new LinkedHashMap<>(matchResultLog));
    }

    private static int calculateDealerProfit(int totalGamblersProfit) {
        return -(totalGamblersProfit);
    }
}