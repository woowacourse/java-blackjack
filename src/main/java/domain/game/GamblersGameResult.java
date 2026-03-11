package domain.game;

import java.util.LinkedHashMap;
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
                                entry.getValue()),
                                (a, b) -> a, LinkedHashMap::new));
    }

    public GameResult getMatchResult(String name) {
        return gamblersResult.get(name);
    }

    public int countDealerWin() {
        return (int) gamblersResult.values()
                .stream()
                .filter(result -> result == GameResult.LOSE)
                .count();
    }

    public int countDealerLose() {
        return (int) gamblersResult.values()
                .stream()
                .filter(result -> result == GameResult.WIN)
                .count();
    }

    public int countDealerDraw() {
        return (int) gamblersResult
                .values()
                .stream()
                .filter(result -> result == GameResult.DRAW)
                .count();
    }

    public Map<String, String> getResultInfo() {
        return gamblersResult.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().getGameResult(),
                        (a, b) -> a, LinkedHashMap::new
                ));
    }
}
