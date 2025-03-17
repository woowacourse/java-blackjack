package game;

import java.util.ArrayList;
import java.util.List;

public class GameResults {

    private final List<GameResult> gameResults;

    public GameResults(List<GameResult> gameResults) {
        this.gameResults = gameResults;
    }

    public static GameResults determine(Dealer dealer, List<Player> players) {
        List<GameResult> gameResults = new ArrayList<>();
        players.forEach(player -> gameResults.add(
                GameResult.determine(dealer, player)
        ));
        return new GameResults(gameResults);
    }

    public List<GameResult> getGameResults() {
        return gameResults;
    }
}
