package blackjack.dto;

import blackjack.domain.game.Result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class DealerPlayerResultResponse {

    private final Map<Result, Integer> dealerResult;
    private final Map<String, Result> playerResult;

    public DealerPlayerResultResponse(Map<Result, Integer> dealerResult, Map<String, Result> playerResult) {
        this.dealerResult = new LinkedHashMap<>(dealerResult);
        this.playerResult = new LinkedHashMap<>(playerResult);
    }

    public Map<Result, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }

    public Map<String, Result> getPlayerResult() {
        return Collections.unmodifiableMap(playerResult);
    }
}
