package blackjack.domain.game;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Profit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResults {

    Map<Gambler, Profit> gameResults;

    public GameResults(Dealer dealer, List<Gambler> gamblers) {
        gameResults = new HashMap<>();
        gamblers.forEach(
                gambler ->
                        gameResults.put(gambler, gambler.getProfit(GameResult.getGameResult(dealer, gambler)))
        );
    }

    public Profit getGameResult(Gambler gambler) {
        return gameResults.get(gambler);
    }

    public Profit getDealerProfit() {
        return gameResults.values().stream()
                .reduce(new Profit(0), Profit::addProfit).negate();
    }
}
