package blackjack.domain;

import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.player.Player;

public class ScoreResult {

    private final Map<Score, Integer> dealerResult;
    private final Map<String, Score> playerResult;

    public ScoreResult(Map<Score, Integer> dealerResult, Map<String, Score> playerResult){
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
                e -> e.getKey(),
                e -> e.getValue().getValue()
            ));
    }
}
