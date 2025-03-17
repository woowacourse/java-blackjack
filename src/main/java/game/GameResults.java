package game;

import java.util.ArrayList;
import java.util.List;

public class GameResults {

    private final List<GameResult> gameResults;

    public GameResults(List<GameResult> gameResults) {
        this.gameResults = gameResults;
    }

    public static GameResults of(Dealer dealer, Players players) {
        List<GameResult> gameResults = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            gameResults.add(GameResult.of(dealer, player));
        }
        return new GameResults(gameResults);
    }

    public List<GameResult> getGameResults() {
        return gameResults;
    }
}
