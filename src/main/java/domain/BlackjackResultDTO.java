package domain;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public record BlackjackResultDTO(Map<Player, Entry<Integer, Integer>> results) {

    public Integer getWin(Player player) {
        return results.get(player).getKey();
    }

    public Integer getLose(Player player) {
        return results.get(player).getValue();
    }
}
