package blackjack.domain;

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

    public int getDealerWin() {
        return (int) gameResults.entrySet().stream().filter(entry ->
                entry.getValue().equals(GameResult.WIN)
        ).count();
    }

    public int getDealerLose() {
        return (int) gameResults.entrySet().stream().filter(entry ->
                entry.getValue().equals(GameResult.LOSE)
        ).count();
    }

    public int getDealerDraw() {
        return (int) gameResults.entrySet().stream().filter(entry ->
                entry.getValue().equals(GameResult.DRAW)
        ).count();
    }
}
