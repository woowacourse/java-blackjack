package blackjack.domain;

import java.util.EnumMap;
import java.util.Map;

public class FinalResult {
    private final Map<Name, WinStatus> playersWinstatus;

    public FinalResult(final Map<Name, WinStatus> finalResult) {
        this.playersWinstatus = finalResult;
    }

    public Map<WinStatus, Integer> summarizeDealerResult() {
        Map<WinStatus, Integer> dealerResult = new EnumMap<>(WinStatus.class);
        for (WinStatus winStatus : playersWinstatus.values()) {
            dealerResult.put(winStatus, dealerResult.getOrDefault(winStatus, 0) + 1);
        }
        return dealerResult;
    }

    public Map<Name, WinStatus> getPlayersWinstatus() {
        return playersWinstatus;
    }
}
