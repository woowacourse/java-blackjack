package domain;

import java.util.HashMap;
import java.util.Map;

public class BetAmount {
    private final Map<Gamer, Integer> betAmounts = new HashMap<>();
    
    public int getAmount(final Gamer gamer) {
        return betAmounts.get(gamer);
    }

    public void save(final Map<Gamer, Integer> bettingAmounts) {
        betAmounts.putAll(bettingAmounts);
    }
}
