package blackjack.domain.player.dto;

import blackjack.domain.player.Name;
import java.util.Map;

public class GameResultDTO {
    private final Map<Name, Integer> gameResult;
    private final int dealerResult;

    public GameResultDTO(Map<Name, Integer> gameResult, int dealerResult) {
        this.gameResult = gameResult;
        this.dealerResult = dealerResult;
    }

    public Map<Name, Integer> getGameResult() {
        return gameResult;
    }

    public int getDealerResult() {
        return dealerResult;
    }
}
