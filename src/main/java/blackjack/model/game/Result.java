package blackjack.model.game;

import blackjack.model.player.Dealer;
import blackjack.model.player.User;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum Result {

    WIN("승"),
    DRAW("무"),
    LOSE("패");

    private final String name;

    Result(final String name) {
        this.name = name;
    }

    public static Map<Result, Integer> evaluateDealerResults(final Dealer dealer, final List<User> users) {
        HashMap<Result, Integer> results = new LinkedHashMap<>(values().length);
        results.put(WIN, 0);
        results.put(DRAW, 0);
        results.put(LOSE, 0);

        users.forEach(user -> results.merge(evaluateDealerResult(dealer, user), 1, Integer::sum));
        return results;
    }

    private static Result evaluateDealerResult(final Dealer dealer, final User user) {
        if (dealer.isDraw(user)) {
            return DRAW;
        }
        if (dealer.isWin(user)) {
            return WIN;
        }
        return LOSE;
    }

    public static Result evaluateUserResult(final Dealer dealer, final User user) {
        if (dealer.isDraw(user)) {
            return DRAW;
        }
        if (dealer.isWin(user)) {
            return LOSE;
        }
        return WIN;
    }

    public String getName() {
        return name;
    }
}
