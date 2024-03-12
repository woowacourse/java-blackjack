package domain.game;

import static domain.game.GameResult.values;

import domain.user.Player;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerResults {
    private final Map<Player, GameResult> playerResults;

    public PlayerResults(Map<Player, GameResult> playerResults) {
        this.playerResults = playerResults;
    }

    public Map<GameResult, Integer> generateDealerResult() {
        return Arrays.stream(values())
                .collect(Collectors.toMap(
                        GameResult::reverse,
                        this::countResults
                ));
    }

    private int countResults(GameResult gameResult) {
        return (int) playerResults.values()
                .stream()
                .filter(playerResult -> playerResult == gameResult)
                .count();
    }

    public Map<Player, GameResult> getPlayerResults() {
        return Collections.unmodifiableMap(playerResults);
    }
}
