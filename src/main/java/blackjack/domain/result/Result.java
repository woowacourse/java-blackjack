package blackjack.domain.result;

import java.util.List;

public class Result {
    private final List<GamePlayerResult> gamePlayerResults;
    private final DealerResult dealerResult;

    public Result(List<GamePlayerResult> gamePlayerResults, DealerResult dealerResult) {
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
