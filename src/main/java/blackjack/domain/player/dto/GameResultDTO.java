package blackjack.domain.player.dto;

import blackjack.domain.ResultType;
import blackjack.domain.player.Name;
import java.util.Map;

public class GameResultDTO {
    private final Map<Name, ResultType> gameResult;
    private final Map<ResultType, Integer> dealerResult;

    public GameResultDTO(Map<Name, ResultType> gameResult, Map<ResultType, Integer> dealerResult) {
        this.gameResult = gameResult;
        this.dealerResult = dealerResult;
    }

    public Map<Name, ResultType> getGameResult() {
        return gameResult;
    }

    public Map<ResultType, Integer> getDealerResult() {
        return dealerResult;
    }
}
