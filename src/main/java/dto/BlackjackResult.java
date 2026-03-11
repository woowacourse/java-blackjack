package dto;

import java.util.List;
import java.util.Map;

public record BlackjackResult(
        int dealerProfit,
        Map<String,Integer> matchResultLog
) {
    public static BlackjackResult from(Map<String,Integer> matchResultLog){
        int dealerProfit = matchResultLog.values().stream()
                .mapToInt(Integer::intValue).sum();
        return new BlackjackResult(-dealerProfit, matchResultLog);
    }
}
