package domain.betting;

import domain.participant.Name;
import java.util.Map;

public class BettingReceipt {

    private final Map<Name, BettingMoney> bettingReceipt;

    public BettingReceipt(Map<Name, BettingMoney> bettingReceipt) {
        this.bettingReceipt = Map.copyOf(bettingReceipt);
    }

    public BettingMoney getBettingMoney(Name name) {
        return bettingReceipt.get(name);
    }
}
