package blackjack.domain;

import blackjack.domain.player.Name;
import blackjack.domain.player.Names;
import java.util.List;

public class BattingAmounts {
    private final List<BattingAmount> battingAmounts;

    private BattingAmounts(List<BattingAmount> battingAmounts) {
        this.battingAmounts = battingAmounts;
    }

    public static BattingAmounts from(List<String> battingAmounts) {
        return new BattingAmounts(battingAmounts.stream()
                                                .map(BattingAmount::new)
                                                .toList());
    }

    public BattingAmount getBattingAmount(int index) {
        return battingAmounts.get(index);
    }
}
