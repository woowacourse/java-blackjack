package blackjack.domain;

import blackjack.domain.player.Player;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerResult {

    private final Map<String, GameResult> userResults;
    private final Map<GameResult, Integer> dealerResultCount;

    private PlayerResult(Map<String, GameResult> userResults, Map<GameResult, Integer> dealerResultCount) {
        this.userResults = userResults;
        this.dealerResultCount = dealerResultCount;
    }

    public static PlayerResult create(int dealerScore, List<Player> players) {
        Map<String, GameResult> userResults = new HashMap<>();
        Map<GameResult, Integer> dealerResultCount = initializeDealerResultCount();

        for (Player player : players) {
            GameResult userResult = player.findResult(dealerScore);

            addUserResult(userResults, player, userResult);
            increaseDealerResultCountByUserResult(dealerResultCount, GameResult.findDealerResult(userResult));
        }

        return new PlayerResult(userResults, dealerResultCount);
    }

    private static void addUserResult(Map<String, GameResult> userResults, Player player, GameResult userResult) {
        userResults.put(player.getName(), userResult);
    }

    private static void increaseDealerResultCountByUserResult(Map<GameResult, Integer> dealerResultCount,
            GameResult dealerResult) {
        dealerResultCount.compute(dealerResult, (resultType, count) -> count + 1);
    }

    private static Map<GameResult, Integer> initializeDealerResultCount() {
        return new HashMap<>(Map.ofEntries(
                Map.entry(GameResult.WIN, 0),
                Map.entry(GameResult.LOSE, 0),
                Map.entry(GameResult.DRAW, 0)
        ));
    }

    public Map<String, GameResult> getUserResults() {
        return Collections.unmodifiableMap(userResults);
    }

    public Map<GameResult, Integer> getDealerResultCount() {
        return Collections.unmodifiableMap(dealerResultCount);
    }
}
