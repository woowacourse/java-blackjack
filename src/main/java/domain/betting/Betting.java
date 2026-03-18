package domain.betting;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Betting {

    private final Map<String, BettingAmount> values = new LinkedHashMap<>();

    public Betting(List<String> names) {
        for (String name : names) {
            values.put(name, null);
        }
    }

    public void betBettingAmount(String name, BettingAmount bettingAmount) {
        values.put(name, bettingAmount);
    }

    public BettingAmount getBettingAmountByName(String name) {
        return values.get(name);
    }

    public Map<String, BettingAmount> getBettingAmounts() {
        return values;
    }
}
