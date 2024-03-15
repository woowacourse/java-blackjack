package blackjack.domain.result;

import blackjack.domain.player.Player;
import java.util.Map;

public class PlayerGameResult {

    private final Map<Player, GameResult> playerGameResultMap;

    public PlayerGameResult(Map<Player, GameResult> playerGameResultMap) {
        this.playerGameResultMap = playerGameResultMap;
    }

    public GameResult findGameResultOfPlayer(Player player) {
        return playerGameResultMap.get(player);
    }
}
