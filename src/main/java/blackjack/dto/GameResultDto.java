package blackjack.dto;

import blackjack.model.GameResult;
import blackjack.model.Player;

import java.util.Collections;
import java.util.Map;

public class GameResultDto {

    private final Map<Player, GameResult> gameResult;

    public GameResultDto(Map<Player, GameResult> gameResult) {
        this.gameResult = gameResult;
    }

    public Map<Player, GameResult> getGameResult() {
        return Collections.unmodifiableMap(gameResult);
    }
}
