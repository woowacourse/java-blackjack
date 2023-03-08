package blackjack.dto;

import java.util.List;

public class TotalGameResult {
    private final int dealerGameResult;
    private final List<PlayerGameResult> playerGameResults;

    public TotalGameResult(int dealerGameResult, List<PlayerGameResult> playerGameResults) {
        this.dealerGameResult = dealerGameResult;
        this.playerGameResults = playerGameResults;
    }

    public static TotalGameResult of(List<PlayerGameResult> playerGameResults) {
        int dealerGameResult = playerGameResults.stream()
                .mapToInt(PlayerGameResult::getResult)
                .sum() * -1;
        return new TotalGameResult(dealerGameResult, playerGameResults);
    }

    public int getDealerGameResult() {
        return dealerGameResult;
    }

    public List<PlayerGameResult> getPlayerGameResults() {
        return playerGameResults;
    }
}
