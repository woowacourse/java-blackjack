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
        final int playersProfitSum = profits.values()
                .stream()
                .mapToInt(Profit::value)
                .sum();
        return new Profit(-playersProfitSum);
    }

    public Map<Name, Profit> details() {
        return Collections.unmodifiableMap(profits);
    }
}
