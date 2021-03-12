package blackjack.domain.batting;

import blackjack.domain.player.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class BettingResult {

    private final Map<Player, Double> profitResults;
    private final double dealerProfit;

    public BettingResult(Map<Player, Double> profitResults, double dealerProfit) {
        this.profitResults = profitResults;
        this.dealerProfit = dealerProfit;
    }

    public static BettingResult of(Map<Player, Double> profitResults, double dealerProfit) {
        return new BettingResult(new LinkedHashMap<>(profitResults), dealerProfit);
    }

    public Map<Player, Double> getGamersProfit() {
        return new LinkedHashMap<>(profitResults);
    }

    public double getDealerProfit() {
        return dealerProfit;
    }
}
