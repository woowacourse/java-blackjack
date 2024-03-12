package blackjack.dto;

import blackjack.domain.GameResult;
import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class PlayerResult {

    private final Map<String, GameResult> result;

    public PlayerResult() {
        this.result = new HashMap<>();
    }

    public void addResult(final Player player, final GameResult gameResult) {
        result.put(player.getName(), gameResult);
    }

    public GameResult findByName(final String name) {
        return result.get(name);
    }
}
