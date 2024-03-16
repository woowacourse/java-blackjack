package blackjack.domain.participant;

import java.util.Map;

public class ProfitDetails {
    private final Map<Name, Profit> profitDetails;

    public ProfitDetails(Map<Name, Profit> profitDetails) {
        this.profitDetails = profitDetails;
    }

    public Profit calculateDealerProfit() {
        return profitDetails.values().stream()
                .reduce(Profit.from(0), Profit::add)
                .inverse();
    }

    public Map<Name, Profit> getProfitDetails() {
        return profitDetails;
    }
}
