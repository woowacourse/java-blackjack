package domain.participant.dealer;

import domain.participant.attributes.Name;
import domain.result.Profit;

public class DealerResult {

    private final Dealer dealer;
    private Profit profit;

    public DealerResult(final Dealer dealer) {
        this.dealer = dealer;
        this.profit = Profit.defaultAmount();
    }

    public void subtract(final Profit playerProfit) {
        profit = profit.subtract(playerProfit);
    }

    public Name getDealerName() {
        return dealer.name();
    }

    public Profit getProfit() {
        return profit;
    }
}
