package domain.betting;

import domain.participant.Name;
import java.util.Map;

public class BettingReceipt {

    private final Map<Name, BettingMoney> maps;

    public BettingReceipt(Map<Name, BettingMoney> maps) {
        this.maps = Map.copyOf(maps);
    }

    public BettingMoney getBettingMoney(Name name) {
        return maps.get(name);
    }
}
