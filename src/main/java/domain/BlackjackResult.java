package domain;

import java.util.Map;
import java.util.Map.Entry;

public record BlackjackResult(Map<Player, Entry<Integer, Integer>> results) {
    public Integer getWin(final Player player) {
        return results.get(player).getKey();
    }

    public Integer getLose(final Player player) {
        return results.get(player).getValue();
    }
}
