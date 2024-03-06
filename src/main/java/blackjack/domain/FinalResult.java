package blackjack.domain;

import java.util.EnumMap;
import java.util.Map;

public class FinalResult {
    private final Map<PlayerName, WinStatus> playersWinstatus;

    public FinalResult(final Map<PlayerName, WinStatus> finalResult) {
        this.playersWinstatus = finalResult;
    }

    public Map<WinStatus, Integer> summarizeDealerResult() {
        Map<WinStatus, Integer> dealerResult = new EnumMap<>(WinStatus.class);
        for (WinStatus winStatus : playersWinstatus.values()) {
            dealerResult.put(winStatus, dealerResult.getOrDefault(winStatus, 0) + 1);
        }
        return dealerResult;
    }
}
