package domain;

import java.util.HashMap;
import java.util.Map;

public class BettingManager {
    private final Map<Name, BettingAmount> bettingAmounts;

    public BettingManager(Map<Name, BettingAmount> playerBettingAmountMap) {
        this.bettingAmounts = new HashMap<>(playerBettingAmountMap);
    }

    public BettingAmount getAmount(Name name) {
        return bettingAmounts.get(name);
    }

    public void lose(Name name) {
        bettingAmounts.put(name, BettingAmount.zero());
    }

    public void win(Name name) {
        BettingAmount amount = getAmount(name);
        bettingAmounts.put(name, amount);
    }

    public void blackJackWin(Name name) {
        BettingAmount amount = getAmount(name);
        bettingAmounts.put(name, amount.oneAndAHalfAmount());
    }
}
