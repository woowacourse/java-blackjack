package blackjack.domain;

import blackjack.domain.player.Player;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParticipantResult {

    private final Map<String, GameResult> playerResults;
    private final Map<GameResult, Integer> dealerResultCount;

    private ParticipantResult(Map<String, GameResult> playerResults, Map<GameResult, Integer> dealerResultCount) {
        this.playerResults = playerResults;
        this.dealerResultCount = dealerResultCount;
    }

    public static ParticipantResult create(int dealerScore, List<Player> players) {
        Map<String, GameResult> playerResults = new HashMap<>();
        Map<GameResult, Integer> dealerResultCount = initializeDealerResultCount();

        for (Player player : players) {
            GameResult playerResult = player.findResult(dealerScore);

            addPlayerResult(playerResults, player, playerResult);
            increaseDealerResultCountByPlayerResult(dealerResultCount, GameResult.findDealerResult(playerResult));
        }

        return new ParticipantResult(playerResults, dealerResultCount);
    }

    private static void addPlayerResult(Map<String, GameResult> playerResults, Player player, GameResult playerResult) {
        playerResults.put(player.getName(), playerResult);
    }

    private static void increaseDealerResultCountByPlayerResult(Map<GameResult, Integer> dealerResultCount,
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

    public Map<String, GameResult> getPlayerResults() {
        return Collections.unmodifiableMap(playerResults);
    }

    public Map<GameResult, Integer> getDealerResultCount() {
        return Collections.unmodifiableMap(dealerResultCount);
    }
}
