package blackjack.domain;

import java.util.List;

public class BettingAmounts {
    private final List<BettingAmount> battingAmounts;

    private BettingAmounts(List<BettingAmount> battingAmounts) {
        this.battingAmounts = battingAmounts;
    }

    public static BettingAmounts from(List<String> battingAmounts) {
        return new BettingAmounts(battingAmounts.stream()
                                                .map(BettingAmount::new)
                                                .toList());
    }

    public BettingAmount getBattingAmount(int index) {
        return battingAmounts.get(index);
    }
}
