package blackjack.domain.game;

import blackjack.domain.user.Money;
import blackjack.domain.user.Player;

import java.util.Collections;
import java.util.Map;

public class TotalResult {
    private final Map<Player, Result> totalResult;

    public TotalResult(Map<Player, Result> totalResult) {
        this.totalResult = Collections.unmodifiableMap(totalResult);
    }

    public Money calculateDealerProfit() {
        Money dealerProfit = new Money();
        for (Map.Entry<Player, Result> entry : totalResult.entrySet()) {
            Player player = entry.getKey();
            Result result = entry.getValue();
            dealerProfit = dealerProfit.subtract(player.getProfitByResult(result));
        }
        return dealerProfit;
    }

    public Map<Player, Result> getResult() {
        return totalResult;
    }
}
