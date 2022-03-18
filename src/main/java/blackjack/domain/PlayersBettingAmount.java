package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.player.BettingAmount;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;

public class PlayersBettingAmount {

    private final Map<Player, BettingAmount> profitResult;

    public PlayersBettingAmount(Map<Player, BettingAmount> profitResult) {
        this.profitResult = profitResult;
    }

    public Map<String, Integer> getResult(Dealer dealer) {
        Map<String, Integer> result = new LinkedHashMap<>();
        result.put(dealer.getName(), 0);
        for (Player player : profitResult.keySet()) {
            int profit = getProfit(player, dealer);
            result.put(player.getName(), profit);
            result.merge(dealer.getName(), -profit, Integer::sum);
        }
        return result;
    }

    private int getProfit(Player player, Dealer dealer) {
        BettingAmount bettingAmount = profitResult.get(player);
        return (int)bettingAmount.getDividend(Score.compete(player, dealer));
    }
}
