package blackjack.domain.game;

import blackjack.domain.participant.Player;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackGameResult {
    private final Map<Player, WinningResult> gameResult;

    public BlackjackGameResult(Map<Player, WinningResult> gameResult) {
        this.gameResult = new LinkedHashMap<>(gameResult);
    }

    public Map<Player, WinningResult> getGameResult() {
        return Collections.unmodifiableMap(gameResult);
    }

    public WinningResult NameByPlayer(Player player) {
        return gameResult.get(player);
    }
}
