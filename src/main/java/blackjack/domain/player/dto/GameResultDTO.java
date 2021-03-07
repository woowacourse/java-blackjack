package blackjack.domain.player.dto;

import blackjack.domain.ResultType;
import blackjack.domain.player.Name;
import java.util.Map;

public class GameResultDTO {
    private final Map<Name, ResultType> gameResult;

    public GameResultDTO(Map<Name, ResultType> gameResult) {
        this.gameResult = gameResult;
    }

    public Map<Name, ResultType> getGameResult() {
        return gameResult;
    }
}
