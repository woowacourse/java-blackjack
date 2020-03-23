package blackjack.domain.game;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Money;
import blackjack.domain.user.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TotalResult {
    private final Map<Player, Result> totalResult;

    public TotalResult(Dealer dealer, List<Player> players) {
        Map<Player, Result> totalResult = new LinkedHashMap<>();
        players.forEach(player -> totalResult.put(player, Result.of(dealer, player)));
        this.totalResult = Collections.unmodifiableMap(totalResult);
    }

    public Money calculateDealerProfit() {
        Money dealerProfit = new Money();
        for (Map.Entry<Player, Result> entry : totalResult.entrySet()) {
            Player player = entry.getKey();
            Result result = entry.getValue();
            dealerProfit = dealerProfit.subtract(player.getProfit(result));
        }
        return dealerProfit;
    }

    public Map<Player, Result> getResult() {
        return totalResult;
    }
}
