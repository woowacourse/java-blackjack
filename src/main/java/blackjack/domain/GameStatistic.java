package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import blackjack.domain.player.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameStatistic {

    private static final int NON_PROFIT_FLAG = -1;

    private final Map<Player, Double> gameResult;

    public GameStatistic(Map<Player, Double> gameResult) {
        this.gameResult = gameResult;
    }

    public static GameStatistic of(Dealer dealer, Gamblers gamblers) {
        Map<Player, Double> result = new LinkedHashMap<>();
        for (Gambler gambler : gamblers.getGamblers()) {
            double profit = gambler.getState().profit(gambler.getMoney(), dealer.getState());
            result.put(gambler, profit);
        }
        return new GameStatistic(result);
    }

    public double profit(Player player) {
        return gameResult.get(player);
    }

    public double getTotalNonProfit() {
        return NON_PROFIT_FLAG * gameResult.values().stream()
            .mapToDouble(Double::doubleValue)
            .sum();
    }

    public Map<Player, Double> getGameResult() {
        return Map.copyOf(gameResult);
    }
}
