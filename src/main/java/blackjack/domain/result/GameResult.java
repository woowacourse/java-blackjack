package blackjack.domain.result;

import blackjack.domain.player.Player;
import java.util.List;
import java.util.Map;

public class GameResult {

    private final Map<Player, List<ResultType>> gameResult;

    private GameResult(Map<Player, List<ResultType>> gameResult) {
        this.gameResult = gameResult;
    }

    public static GameResult of(Map<Player, List<ResultType>> gameResult) {
        return new GameResult(gameResult);
    }

    public List<ResultType> findByPlayer(Player player) {
        return gameResult.get(player);
    }
}
