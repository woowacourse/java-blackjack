package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Gamblers;
import blackjack.domain.player.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameStatistic {

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

    public Map<Player, Double> getGameResult() {
        return Map.copyOf(gameResult);
    }
}
