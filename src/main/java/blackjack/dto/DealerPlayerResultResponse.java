package blackjack.dto;

import java.util.Map;

public class DealerPlayerResultResponse {

    private final double dealerResult;
    private final Map<String, Double> playerResult;

    public DealerPlayerResultResponse(double dealerResult, Map<String, Double> playerResult) {
        this.dealerResult = dealerResult;
        this.playerResult = playerResult;
    }

    public double getDealerResult() {
        return dealerResult;
    }

    public Map<String, Double> getPlayerResult() {
        return playerResult;
    }
}
