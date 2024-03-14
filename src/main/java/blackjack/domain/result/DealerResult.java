package blackjack.domain.result;

import blackjack.domain.Profit;
import blackjack.domain.player.Name;
import java.util.EnumMap;
import java.util.List;

public class DealerResult {
    private final Name name;
    private final Profit profit;

    private DealerResult(Name name, Profit profit) {
        this.name = name;
        this.profit = profit;
    }

    public static DealerResult of(Name name, List<GamePlayerResult> gamePlayerResults) {
        Profit dealerProfit = new Profit(0);
        for (GamePlayerResult gamePlayerResult : gamePlayerResults) {
            dealerProfit = dealerProfit.sum(gamePlayerResult.getProfit());
        }

        return new DealerResult(name, dealerProfit);
    }

    public String getName() {
        return name.asString();
    }

    public Profit getProfit() {
        return profit;
    }
}
