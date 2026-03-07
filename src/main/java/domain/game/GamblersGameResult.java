package domain.game;

import java.util.Map;
import java.util.stream.Collectors;

public class GamblersGameResult {

    private Map<String, GameResult> gamblersResult;

    public GamblersGameResult(int dealerScore, Map<String, Integer> gameResults) {
        this.gamblersResult = gameResults.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> GameResult.determine(
                                dealerScore,
                                entry.getValue())));
    }

    public GameResult getMatchResult(String name) {
        return gamblersResult.get(name);
    }
}
