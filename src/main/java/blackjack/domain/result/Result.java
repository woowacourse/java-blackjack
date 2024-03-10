package blackjack.domain.result;

import java.util.List;

public class Result {
    private final List<GamePlayerResult> gamePlayerResults;
    private final DealerResult dealerResult;

    public Result(final List<GamePlayerResult> gamePlayerResults, final DealerResult dealerResult) {
        this.gamePlayerResults = gamePlayerResults;
        this.dealerResult = dealerResult;
    }

    public List<GamePlayerResult> getGamePlayerResults() {
        return gamePlayerResults;
    }

    public DealerResult getDealerResult() {
        return dealerResult;
    }
}
