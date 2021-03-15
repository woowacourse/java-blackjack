package blackjack.domain.batting;

import blackjack.domain.Money;
import blackjack.domain.player.Player;
import java.util.LinkedHashMap;
import java.util.Map;

public class BettingResult {

    private final Map<Player, Money> profitResults;
    private final Money dealerProfit;

    public BettingResult(Map<Player, Money> profitResults, Money dealerProfit) {
        this.profitResults = profitResults;
        this.dealerProfit = dealerProfit;
    }

    public static BettingResult of(Map<Player, Money> profitResults, Money dealerProfit) {
        return new BettingResult(new LinkedHashMap<>(profitResults), dealerProfit);
    }

    public Map<Player, Money> getGamersProfit() {
        return new LinkedHashMap<>(profitResults);
    }

    public Money getDealerProfit() {
        return dealerProfit;
    }
}
