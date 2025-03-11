package blackjack.model.game;

import blackjack.model.player.Dealer;
import blackjack.model.player.Player;
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

    public static Map<Result, Integer> evaluateDealerResults(final Dealer dealer, final List<Player> players) {
        HashMap<Result, Integer> results = new LinkedHashMap<>(values().length);
        results.put(WIN, 0);
        results.put(DRAW, 0);
        results.put(LOSE, 0);

        players.forEach(user -> results.merge(evaluateDealerResult(dealer, user), 1, Integer::sum));
        return results;
    }

    private static Result evaluateDealerResult(final Dealer dealer, final Player player) {
        if (dealer.isDraw(player)) {
            return DRAW;
        }
        if (dealer.isWin(player)) {
            return WIN;
        }
        return LOSE;
    }

    public static Result evaluateUserResult(final Dealer dealer, final Player player) {
        if (dealer.isDraw(player)) {
            return DRAW;
        }
        if (dealer.isWin(player)) {
            return LOSE;
        }
        return WIN;
    }

    public String getName() {
        return name;
    }
}
