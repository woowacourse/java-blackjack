package blackjack.domain;

import java.util.Map;

public class GameStatistics {
    private final Map<Player, MatchResult> playerResults;

    private GameStatistics(Map<Player, MatchResult> playerResults) {
        this.playerResults = playerResults;
    }

    public static GameStatistics from(Map<Player, MatchResult> playerResults) {
        return new GameStatistics(playerResults);
    }

    public Map<String, Long> getDealerStatistics() {
        return Map.of(
                "승", countResult(MatchResult.LOSE),
                "패", countResult(MatchResult.WIN),
                "무", countResult(MatchResult.DRAW)
        );
    }

    private long countResult(MatchResult target) {
        return playerResults.values().stream()
                .filter(result -> result == target)
                .count();
    }
}
