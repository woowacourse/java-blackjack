package blackjack.domain.participant;

import java.util.Map;

public class PlayerProfitAmounts {
    private final Map<Name, ProfitAmount> profitAmounts;

    public PlayerProfitAmounts(Map<Name, ProfitAmount> profitAmounts) {
        this.profitAmounts = profitAmounts;
    }

    public ProfitAmount calculateDealerProfit() {
        return profitAmounts.values().stream()
                .reduce(new ProfitAmount(0), ProfitAmount::add)
                .inverse();
    }

    public Map<Name, ProfitAmount> getProfitAmounts() {
        return profitAmounts;
    }
}
