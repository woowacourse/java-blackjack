package blackjack.domain;


import blackjack.domain.user.Dealer;
import blackjack.domain.user.Player;
import blackjack.domain.user.Players;
import blackjack.domain.user.User;

import java.util.*;

public class GameResult {
    private static final int DEALER_PROFIT_FACTOR = -1;
    private static final int DEFAULT_DEALER_PROFIT = 0;
    private Map<User, Integer> gameResult;

    private GameResult(Map<User, Integer> result) {
        this.gameResult = result;
    }

    public static GameResult calculateGameResult(Dealer dealer, Players players) {
        Map<User, Integer> result = new LinkedHashMap<>();
        result.put(dealer, DEFAULT_DEALER_PROFIT);
        for (Player player : players.getPlayers()) {
            result.put(player, dealer.calculatePlayerProfit(player));
        }
        result.put(dealer, calculateDealerProfit(result));
        return new GameResult(result);
    }

    private static int calculateDealerProfit(Map<User, Integer> result) {
        return DEALER_PROFIT_FACTOR * result.keySet()
                .stream()
                .mapToInt(result::get)
                .sum();
    }

    public Map<User, Integer> getGameResult() {
        return Collections.unmodifiableMap(gameResult);
    }

    public int get(User user) {
        return gameResult.get(user);
    }
}
