package blackjack.domain.player;

import java.util.ArrayList;
import java.util.List;

public class BettingAmounts {
    private final List<BettingAmount> bettingAmounts;

    private BettingAmounts(List<BettingAmount> battingAmounts) {
        this.bettingAmounts = battingAmounts;
    }

    public static BettingAmounts from(List<String> battingAmounts) {
        return new BettingAmounts(battingAmounts.stream()
                                                .map(BettingAmount::new)
                                                .toList());
    }

    public List<BettingAmount> getBettingAmounts() {
        return new ArrayList<>(bettingAmounts);
    }
}
