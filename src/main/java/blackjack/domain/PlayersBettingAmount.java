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

    public Map<Player, Integer> getResult(Dealer dealer) {
        Map<Player, Integer> result = new LinkedHashMap<>();
        Player copyDealer = dealer.copy();
        result.put(copyDealer, 0);
        for (Player player : profitResult.keySet()) {
            int profit = getProfit(player, dealer);
            result.put(player.copy(), profit);
            result.merge(copyDealer, -profit, Integer::sum);
        }
        return result;
    }

    private int getProfit(Player player, Dealer dealer) {
        BettingAmount bettingAmount = profitResult.get(player);
        return (int)bettingAmount.getDividend(player.compete(dealer));
    }
}
