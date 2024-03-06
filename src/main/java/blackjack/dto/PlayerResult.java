package blackjack.dto;

import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class PlayerResult {
    private final Map<String, Boolean> result = new HashMap<>();

    public PlayerResult() {
    }

    public void addResult(final Player player, final boolean result) {
        this.result.put(player.getName(), result);
    }

    public boolean findByName(final String name) {
        return result.get(name);
    }
}
