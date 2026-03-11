package domain.result;

import java.util.HashMap;
import java.util.Map;

public class ProfitMap {
    private final Map<String, Long> map = new HashMap<>();

    public void addProfitOf(String playerName, long amount) {
        map.put(playerName, amount);
    }

    public Map<String, Long> getMap() {
        return Map.copyOf(map);
    }

    public long sumProfits() {
        return map.values().stream().reduce(0L, Long::sum);
    }
}
