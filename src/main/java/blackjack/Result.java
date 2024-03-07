package blackjack;

import java.util.List;

public class Result {
    private List<GamePlayerResult> gamePlayerResults;
    private DealerResult dealerResult;

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
