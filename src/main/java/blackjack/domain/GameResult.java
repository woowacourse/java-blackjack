package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private final Map<Player, Profit> gameResult;

    private GameResult(Map<Player, Profit> gameResult) {
        this.gameResult = gameResult;
    }

    public static GameResult of(Dealer dealer, Players players) {
        return new GameResult(makeGameResult(dealer, players));
    }

    private static Map<Player, Profit> makeGameResult(Dealer dealer, Players players) {
        Map<Player, Profit> gameResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            double profit = Result.calculateProfit(player, dealer);
            gameResult.put(player, Profit.from(profit));
        }
        return gameResult;
    }

    public Double getSumOfProfit() {
        return gameResult.values()
                .stream()
                .mapToDouble(Profit::getProfit)
                .sum();
    }

    public Map<Player, Profit> getGameResult() {
        return gameResult;
    }
}
