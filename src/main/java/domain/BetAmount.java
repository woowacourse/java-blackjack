package domain;

import java.util.HashMap;
import java.util.Map;

public class BetAmount {
    private final Map<Gamer, Integer> betAmounts = new HashMap<>();

    public void saveAmount(final Gamer gamer, final int amount) {
        betAmounts.put(gamer, amount);
    }

    public int getAmount(final Gamer gamer) {
        return betAmounts.get(gamer);
    }
}
