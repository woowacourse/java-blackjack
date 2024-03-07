package blackjack.domain;

import java.util.EnumMap;
import java.util.Map;

public class WinningResult {
    private final Map<ParticipantName, WinStatus> participantsWinStatus;

    public WinningResult(final Map<ParticipantName, WinStatus> participantsWinStatus) {
        this.participantsWinStatus = participantsWinStatus;
    }

    public Map<WinStatus, Integer> summarizeDealerResult() {
        final Map<WinStatus, Integer> dealerResult = new EnumMap<>(WinStatus.class);

        for (WinStatus winStatus : participantsWinStatus.values()) {
            final WinStatus dealerStatus = winStatus.opposite();
            dealerResult.put(dealerStatus, dealerResult.getOrDefault(dealerStatus, 0) + 1);
        }

        return dealerResult;
    }
}
