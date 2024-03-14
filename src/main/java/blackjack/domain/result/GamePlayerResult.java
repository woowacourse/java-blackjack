package blackjack.domain.result;

import blackjack.domain.Profit;
import blackjack.domain.player.Name;

public class GamePlayerResult {
    private final Name name;
    private final Profit profit;

    public GamePlayerResult(Name name, Profit profit) {
        this.name = name;
        this.profit = profit;
    }

    public String getName() {
        return name.asString();
    }

    public Profit getProfit() {
        return profit;
    }
}
