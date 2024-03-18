package blackjack.domain.participant;

import java.util.Collections;
import java.util.Map;

public class PlayerBetAmounts {
    private final Map<Name, BetAmount> betAmounts;

    public PlayerBetAmounts(Map<Name, BetAmount> betAmounts) {
        this.betAmounts = betAmounts;
    }

    public BetAmount getAmountBy(Name name) {
        return betAmounts.get(name);
    }

    public Map<Name, BetAmount> getAmounts() {
        return Collections.unmodifiableMap(betAmounts);
    }
}
