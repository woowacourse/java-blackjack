package blackjack.domain.betting;

import blackjack.domain.participant.Name;
import java.util.HashMap;
import java.util.Map;

public class BettingManager {

    private final Map<Name, Betting> bettingInfo;

    public BettingManager() {
        this.bettingInfo = new HashMap<>();
    }

    public void registerBetting(final Name name, final Betting betting) {
        bettingInfo.put(name, betting);
    }

    public Betting findBettingByName(final String name) {
        return bettingInfo.get(new Name(name));
    }
}
