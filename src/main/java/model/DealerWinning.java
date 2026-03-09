package model;

import constant.MatchStatus;
import java.util.LinkedHashMap;
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

    public Map<MatchStatus, Integer> getDealerWinning() {
        return Map.copyOf(dealerWinning);
    }
}
