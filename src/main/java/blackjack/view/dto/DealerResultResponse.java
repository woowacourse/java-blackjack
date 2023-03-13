package blackjack.view.dto;

import blackjack.domain.participant.Dealer;
import blackjack.domain.result.Result;
import java.util.Map;

public class DealerResultResponse {

    private final String name;
    private final int profit;

    public DealerResultResponse(final String name, final int profit) {
        this.name = name;
        this.profit = profit;
    }

    public static DealerResultResponse of(final Dealer dealer, final int profit) {
        return new DealerResultResponse(dealer.getName(), profit);
    }

    public String getName() {
        return name;
    }

    public int getProfit() {
        return profit;
    }
}
