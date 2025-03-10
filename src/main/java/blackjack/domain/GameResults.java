package blackjack.domain;

import blackjack.domain.player.Player;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResults {

    private final Map<Player, GameResult> gameResults;

    public GameResults(final Player dealer, final List<Player> gamblers) {
        gameResults = new HashMap<>();
        gamblers.forEach(
                player -> {
                    gameResults.put(player, GameResult.evaluateGameResult(dealer, player));
                }
        );
    }

    public GameResult getGameResult(final Player player) {
        return gameResults.get(player);
    }

    public int getDealerWin() {
        return (int) gameResults.entrySet().stream().filter(entry ->
                entry.getValue().equals(GameResult.LOSE)
        ).count();
    }

    public int getDealerLose() {
        return (int) gameResults.entrySet().stream().filter(entry ->
                entry.getValue().equals(GameResult.WIN)
        ).count();
    }

    public int getDealerDraw() {
        return (int) gameResults.entrySet().stream().filter(entry ->
                entry.getValue().equals(GameResult.DRAW)
        ).count();
    }
}
