package blackjack.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameFinalResult {
    private final GameRule gameRule;

    public GameFinalResult(GameRule gameRule) {
        this.gameRule = gameRule;
    }

    public Map<GameResult, Integer> getDealerFinalResult(Dealer dealer, List<Player> players) {
        Map<GameResult, Integer> gameFinalResult = new HashMap<>();
        for (Player player : players) {
            GameResult result = gameRule.evaluateDealerWin(player, dealer);
            gameFinalResult.put(result, gameFinalResult.getOrDefault(result, 0) + 1);
        }
        return gameFinalResult;
    }

    public GameResult getGameResultFromPlayer(Player player, Dealer dealer) {
        return gameRule.evaluatePlayerWin(player, dealer);
    }
}
