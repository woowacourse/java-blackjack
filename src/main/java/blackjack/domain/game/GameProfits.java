package blackjack.domain.game;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Profit;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameProfits {

    Map<Gambler, Profit> gameProfits;

    public GameProfits(Dealer dealer, List<Gambler> gamblers) {
        gameProfits = new HashMap<>();
        gamblers.forEach(
                gambler ->
                        gameProfits.put(gambler, gambler.getProfit(GameResult.getGameResult(dealer, gambler)))
        );
    }

    public Profit getGameResult(Gambler gambler) {
        return gameProfits.get(gambler);
    }

    public Profit getDealerProfit() {
        return gameProfits.values().stream()
                .reduce(new Profit(0), Profit::addProfit).negate();
    }
}
