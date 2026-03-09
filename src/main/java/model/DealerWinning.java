package model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DealerWinning {
    private final Map<MatchStatus, Integer> dealerWinning = new LinkedHashMap<>();

    public DealerWinning() {
        for(MatchStatus matchStatus : MatchStatus.values()) {
            dealerWinning.put(matchStatus, 0);
        }
    }

    public void increase(MatchStatus matchStatus) {
        dealerWinning.put(matchStatus, dealerWinning.get(matchStatus) + 1);
    }

    public List<String> getFormattedDealerWinning() {
        return dealerWinning.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .map(entry -> entry.getValue() + entry.getKey().getStatus())
                .toList();
    }
}
