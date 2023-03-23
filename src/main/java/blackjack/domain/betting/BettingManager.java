package blackjack.domain.betting;

import blackjack.domain.participant.Name;
import java.util.HashMap;
import java.util.Map;

public class BettingManager {

    private final Map<Name, Betting> bettingInfo;

    public BettingManager() {
        this.bettingInfo = new HashMap<>();
    }

    public void registerBetting(final String name, final int bettingAmount) {
        bettingInfo.put(new Name(name), new Betting(bettingAmount));
    }

    public Betting findBettingByName(final String name) {
        return bettingInfo.get(new Name(name));
    }
}
