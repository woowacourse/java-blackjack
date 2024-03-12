package blackjack.dto;

import blackjack.domain.GameResult;
import blackjack.domain.participant.Player;
import java.util.HashMap;
import java.util.Map;

public class PlayerResult {

    private static final String NONEXISTENT_NAME_EXCEPTION = "존재하지 않는 참가자 이름입니다.";

    private final Map<String, GameResult> result;

    public PlayerResult() {
        this.result = new HashMap<>();
    }

    public void addResult(final Player player, final GameResult gameResult) {
        result.put(player.getName(), gameResult);
    }

    public GameResult findByName(final String name) {
        if (!result.containsKey(name)) {
            throw new IllegalArgumentException(NONEXISTENT_NAME_EXCEPTION);
        }

        return result.get(name);
    }
}
