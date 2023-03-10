package blackjack.dto;

import java.util.List;

public class TotalGameResultResponse {
    private final int dealerProfit;
    private final List<PlayerGameResult> playerGameResults;

    public TotalGameResultResponse(int dealerProfit, List<PlayerGameResult> playerGameResults) {
        this.dealerProfit = dealerProfit;
        this.playerGameResults = playerGameResults;
    }

    public int getDealerProfit() {
        return dealerProfit;
    }

    public List<PlayerGameResult> getPlayerGameResults() {
        return playerGameResults;
    }
}
