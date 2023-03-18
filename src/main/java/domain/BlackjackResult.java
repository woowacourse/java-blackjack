package domain;

import java.util.List;

public final class BlackjackResult {
    private final List<PlayerResult> playerResults;
    private final DealerResult dealerResult;

    public BlackjackResult(final List<PlayerResult> playerResults, final DealerResult dealerResult) {
        this.playerResults = playerResults;
        this.dealerResult = dealerResult;
    }

    public List<PlayerResult> getPlayerResults() {
        return playerResults;
    }

    public DealerResult getDealerResult() {
        return dealerResult;
    }
}
