package blackjack.domain;

import blackjack.domain.player.Player;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResult {

    private final Map<String, Result> userResult;
    private final Map<Result, Integer> dealerResult;

    private GameResult(Map<String, Result> userResult, Map<Result, Integer> dealerResult) {
        this.userResult = userResult;
        this.dealerResult = dealerResult;
    }

    public static GameResult createPlayerGameResult(Player dealer, List<Player> users) {
        Map<String, Result> userResult = new HashMap<>();
        Map<Result, Integer> dealerResult = initializeDealerResultCount();
        for (Player user : users) {
            userResult.put(user.getName(), user.findResult(dealer));
            dealerResult.compute(dealer.findResult(user), (result, count) -> count + 1);
        }
        return new GameResult(userResult, dealerResult);
    }

    private static Map<Result, Integer> initializeDealerResultCount() {
        return new HashMap<>(Map.ofEntries(
                Map.entry(Result.WIN, 0),
                Map.entry(Result.LOSE, 0),
                Map.entry(Result.DRAW, 0)
        ));
    }

    public Map<String, Result> getUserResult() {
        return Collections.unmodifiableMap(userResult);
    }

    public Map<Result, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }
}
