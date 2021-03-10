package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class GameResult {
    private final Map<Player, Integer> gameResult;

    private GameResult(Map<Player, Integer> gameResult) {
        this.gameResult = gameResult;
    }

    public static GameResult of(Dealer dealer, List<Gamer> gamers) {
        Map<Player, Integer> result = new LinkedHashMap<>();
        int dealerProfit = 0;
        for (Gamer gamer : gamers) {
            result.put(gamer, gamer.getProfit(dealer));
            dealerProfit -= gamer.getProfit(dealer);
        }
        result.put(dealer, dealerProfit);
        return new GameResult(result);
    }

    public int findProfitByPlayer(Player player) {
        return gameResult.get(player);
    }
}