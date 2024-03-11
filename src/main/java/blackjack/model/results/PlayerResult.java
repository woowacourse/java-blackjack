package blackjack.model.results;

import blackjack.model.participants.Player;
import java.util.Map;

public class PlayerResult {
    private final Map<Player, Result> result;

    public PlayerResult(Map<Player, Result> result) {
        this.result = result;
    }

    public Map<Player, Result> getResult() {
        return result;
    }
}
