package blackjack.dto;

import blackjack.domain.GameResult;
import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class PlayerResult {

    private final Map<String, GameResult> result;

    public PlayerResult() {
        result = new HashMap<>();
    }

    public void addResult(final Player player, final GameResult result) {
        this.result.put(player.getName(), result);
    }

    public GameResult findByName(final String name) {
        return result.get(name);
    }
}
