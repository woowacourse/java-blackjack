package blackjack.domain;

import java.util.EnumMap;
import java.util.Map;

public class WinningResult {
    private final Map<ParticipantsName, WinStatus> playersWinstatus;

    public WinningResult(final Map<ParticipantsName, WinStatus> winningResult) {
        this.playersWinstatus = winningResult;
    }

    public Map<WinStatus, Integer> summarizeDealerResult() {
        Map<WinStatus, Integer> dealerResult = new EnumMap<>(WinStatus.class);
        for (WinStatus winStatus : playersWinstatus.values()) {
            dealerResult.put(winStatus.opposite(), dealerResult.getOrDefault(winStatus.opposite(), 0) + 1);
        }
        return dealerResult;
    }
}
