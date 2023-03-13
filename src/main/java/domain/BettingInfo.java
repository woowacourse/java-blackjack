package domain;

import java.util.HashMap;
import java.util.Map;

public class BettingInfo {
    private final Map<String, Integer> bettingAmounts;

    public BettingInfo(final Map<String, Integer> bettingAmounts) {
        this.bettingAmounts = Map.copyOf(bettingAmounts);
    }

    public Map<String, Integer> getEarnings(final Map<String, PlayerOutcome> playersOutcome) {
        final Map<String, Integer> earnings = new HashMap<>();
        for (final String name : bettingAmounts.keySet()) {
            final PlayerOutcome playerOutcome = playersOutcome.get(name);
            final int earning = playerOutcome.calculateEarning(bettingAmounts.get(name));
            earnings.put(name, earning);
        }
        return earnings;
    }
}
