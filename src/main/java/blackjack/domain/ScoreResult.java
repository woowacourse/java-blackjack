package blackjack.domain;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class ScoreResult {

    private final Map<Score, Integer> dealerResult;
    private final Map<String, Score> playerResult;

    ScoreResult(Map<Score, Integer> dealerResult, Map<String, Score> playerResult) {
        this.dealerResult = dealerResult;
        this.playerResult = playerResult;
    }

    public Integer getDealerScoreCount(Score score) {
        return dealerResult.get(score);
    }

    public Score getPlayerScore(Player player) {
        return playerResult.get(player.getName());
    }

    public Map<String, String> getPlayerResult() {
        return playerResult.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Entry::getKey,
                        e -> e.getValue().getValue()
                ));
    }
}
