package blackjack.domain;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

public class ScoreResult {

    private static final Map<Score, Integer> cachingDealerResult = new EnumMap<Score, Integer>(Score.class);

    private final Map<Score, Integer> dealerResult;
    private final Map<String, Score> playerResult;

    static {
        for (Score value : Score.values()) {
            cachingDealerResult.put(value, 0);
        }
    }

    private ScoreResult(Map<Score, Integer> dealerResult, Map<String, Score> playerResult){
        this.dealerResult = dealerResult;
        this.playerResult = playerResult;
    }

    public static ScoreResult from(List<Player> players, Dealer dealer) {
        Map<Score, Integer> dealerResult = new HashMap<>(cachingDealerResult);
        Map<String, Score> playerResults = new HashMap<>();

        for (Player player : players) {
            Score score = player.compete(dealer);
            playerResults.put(player.getName(), score);
            dealerResult.merge(Score.inverse(score), 1, Integer::sum);
        }
        return new ScoreResult(dealerResult, playerResults);
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
