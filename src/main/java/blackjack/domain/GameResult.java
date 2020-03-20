package blackjack.domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

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
        players.getPlayers()
                .forEach(player ->
                        result.put(player, calculateResultByDealerStatusStrategy(dealer, player)
                                .calculateProfit(player.getBettingMoney()))
                );
        result.put(dealer, calculateDealerProfit(result));
        return new GameResult(result);
    }

    private static int calculateDealerProfit(Map<User, Integer> result) {
        return DEALER_PROFIT_FACTOR * result.keySet()
                .stream()
                .mapToInt(result::get)
                .sum();
    }

    private static PlayerResult calculateResultByDealerStatusStrategy(Dealer dealer, Player player) {
        return dealer.getResultOf(player);
    }

    public Map<User, Integer> getGameResult() {
        return Collections.unmodifiableMap(gameResult);
    }

    public int getProfit(User user) {
        return gameResult.get(user);
    }
}
