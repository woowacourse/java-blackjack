package blackjack.domain.result;

import java.util.Map;

public class PlayerResult {
    private final Map<String, Match> playerResults;

    public PlayerResult(Map<String, Match> playerResults) {
        this.playerResults = playerResults;
    }

    public Map<String, Match> get() {
        return playerResults;
    }
}
