package model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DealerWinning {
    private final Map<MatchStatus, Integer> dealerWinning = new LinkedHashMap<>();
    private Integer totalBet = 0;

    public DealerWinning() {
        for(MatchStatus matchStatus : MatchStatus.values()) {
            dealerWinning.put(matchStatus, 0);
        }
    }

    public void increase(MatchStatus matchStatus, Integer betAmount) {
        dealerWinning.put(matchStatus, dealerWinning.get(matchStatus) + 1);
        totalBet += betAmount;
    }

    public List<String> getFormattedDealerWinning() {
        return dealerWinning.entrySet().stream()
                .filter(entry -> entry.getValue() > 0)
                .map(entry -> entry.getValue() + entry.getKey().getStatus())
                .toList();
    }

    public Integer getTotalBet() {
        return totalBet;
    }
}
