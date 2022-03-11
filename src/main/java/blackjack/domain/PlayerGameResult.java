package blackjack.domain;

import blackjack.domain.player.User;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerGameResult {

    private final Map<String, ResultType> userResults;
    private final Map<ResultType, Integer> dealerResultCount;

    private PlayerGameResult(Map<String, ResultType> userResults, Map<ResultType, Integer> dealerResultCount) {
        this.userResults = userResults;
        this.dealerResultCount = dealerResultCount;
    }

    public static PlayerGameResult create(int dealerScore, List<User> users) {
        Map<String, ResultType> userResults = new HashMap<>();
        Map<ResultType, Integer> dealerResultCount = initializeDealerResultCount();

        for (User user : users) {
            ResultType userResult = user.findResult(dealerScore);

            addUserResult(userResults, user, userResult);
            increaseDealerResultCountByUserResult(dealerResultCount, ResultType.findDealerResult(userResult));
        }

        return new PlayerGameResult(userResults, dealerResultCount);
    }

    private static void addUserResult(Map<String, ResultType> userResults, User user, ResultType userResult) {
        userResults.put(user.getName(), userResult);
    }

    private static void increaseDealerResultCountByUserResult(Map<ResultType, Integer> dealerResultCount,
            ResultType dealerResult) {
        dealerResultCount.compute(dealerResult, (resultType, count) -> count + 1);
    }

    private static Map<ResultType, Integer> initializeDealerResultCount() {
        return new HashMap<>(Map.ofEntries(
                Map.entry(ResultType.WIN, 0),
                Map.entry(ResultType.LOSE, 0),
                Map.entry(ResultType.DRAW, 0)
        ));
    }

    public Map<String, ResultType> getUserResults() {
        return Collections.unmodifiableMap(userResults);
    }

    public Map<ResultType, Integer> getDealerResultCount() {
        return Collections.unmodifiableMap(dealerResultCount);
    }
}
