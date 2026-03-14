package domain.betting;

import domain.participant.Name;
import java.util.HashMap;
import java.util.Map;

public class BettingAmounts {
    private final Map<Name, BettingAmount> bettingAmounts;

    public BettingAmounts(Map<Name, BettingAmount> playerBettingAmountMap) {
        this.bettingAmounts = new HashMap<>(playerBettingAmountMap);
    }

    public BettingAmount getAmount(Name name) {
        return bettingAmounts.get(name);
    }
}
