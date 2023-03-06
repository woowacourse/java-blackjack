package blackjack.dto;

import blackjack.domain.game.Result;

import java.util.Map;

public class DealerPlayerResultResponse {

    private final Map<Result, Integer> dealerResult;
    private final Map<String, Result> playerResult;

    public DealerPlayerResultResponse(Map<Result, Integer> dealerResult, Map<String, Result> playerResult) {
        this.dealerResult = dealerResult;
        this.playerResult = playerResult;
    }

    public Map<Result, Integer> getDealerResult() {
        return dealerResult;
    }

    public Map<String, Result> getPlayerResult() {
        return playerResult;
    }
}
