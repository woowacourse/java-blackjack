package dto;

import java.util.LinkedHashMap;
import java.util.Map;

public record BlackjackResult(
        int dealerProfit,
        LinkedHashMap<String, Integer> matchResultLog
) {
    public static BlackjackResult from(LinkedHashMap<String, Integer> matchResultLog) {
        int dealerProfit = matchResultLog.values().stream()
                .mapToInt(Integer::intValue).sum();
        return new BlackjackResult(-dealerProfit, matchResultLog);
    }
}