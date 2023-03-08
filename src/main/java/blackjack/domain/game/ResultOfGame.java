package blackjack.domain.game;

import java.util.LinkedHashMap;
import java.util.Map;

public class ResultOfGame {

    private final Map<String, ResultType> playerResult;
    private final Map<ResultType, Integer> dealerResult;

    public ResultOfGame(final Map<String, ResultType> playerResult, final Map<ResultType, Integer> dealerResult) {
        this.playerResult = playerResult;
        this.dealerResult = dealerResult;
    }

    public Map<String, ResultType> getPlayerResult() {
        return new LinkedHashMap<>(playerResult);
    }

    public Map<ResultType, Integer> getDealerResult() {
        return new LinkedHashMap<>(dealerResult);
    }

}
