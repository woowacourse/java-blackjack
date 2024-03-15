package blackjack.domain.betting;

import blackjack.domain.player.Name;

import java.util.Collections;
import java.util.Map;

public class ProfitDetails {
    private final Map<Name, Profit> profits;

    public ProfitDetails(final Map<Name, Profit> profits) {
        this.profits = profits;
    }

    public Profit getDealerProfit() {
        int sum = profits.values().stream().mapToInt(Profit::getValue).sum();
        return new Profit(-sum);
    }

    public Map<Name, Profit> details() {
        return Collections.unmodifiableMap(profits);
    }
}
