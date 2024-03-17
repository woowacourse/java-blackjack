package blackjack.domain.player;

import java.util.ArrayList;
import java.util.List;

public class BettingAmounts {
    private final List<BettingAmount> bettingAmounts;

    private BettingAmounts(List<BettingAmount> bettingAmounts) {
        this.bettingAmounts = bettingAmounts;
    }

    public static BettingAmounts from(List<String> bettingAmounts) {
        return new BettingAmounts(bettingAmounts.stream()
                                                .map(BettingAmount::new)
                                                .toList());
    }

    public List<BettingAmount> getBettingAmounts() {
        return new ArrayList<>(bettingAmounts);
    }
}
