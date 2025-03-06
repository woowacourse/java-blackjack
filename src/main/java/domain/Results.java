package domain;

import java.util.List;

public class Results {
    private final DealerResult dealerResult;
    private final List<PlayerResult> playerResults;

    public Results(DealerResult dealerResult, List<PlayerResult> playerResults) {
        this.dealerResult = dealerResult;
        this.playerResults = playerResults;
    }

    public DealerResult getDealerResult() {
        return dealerResult;
    }

    public List<PlayerResult> getPlayerResults() {
        return playerResults;
    }
}
