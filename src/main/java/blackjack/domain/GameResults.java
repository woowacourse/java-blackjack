package blackjack.domain;

import blackjack.domain.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResults {

    Map<Player, GameResult> gameResults;

    public GameResults(Player dealer, List<Player> gamblers) {
        gameResults = new HashMap<>();
        gamblers.forEach(
                player -> {
                    gameResults.put(player, GameResult.getGameResult(dealer, player));
                }
        );
    }

    public GameResult getGameResult(Player player) {
        return gameResults.get(player);
    }

    public int getDealerWin() {
        return getGameResultCount(GameResult.LOSE);
    }

    public int getDealerLose() {
        return getGameResultCount(GameResult.WIN);
    }

    public int getDealerDraw() {
        return getGameResultCount(GameResult.DRAW);
    }

    private int getGameResultCount(GameResult gameResult) {
        return (int) gameResults.entrySet()
                .stream()
                .filter(entry ->
                        entry.getValue().equals(gameResult)
                ).count();
    }
}
