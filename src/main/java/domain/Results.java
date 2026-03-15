package domain;

import java.util.HashMap;
import java.util.Map;

public class Results {
    private final Map<Player, Result> results = new HashMap<>();

    public void add(Player player, Result result) {
        results.put(player, result);
    }

    public Result findByPlayer(Player player) {
        return results.get(player);
    }
}
