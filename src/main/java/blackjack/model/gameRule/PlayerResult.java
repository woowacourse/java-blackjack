package blackjack.model.gameRule;

import blackjack.model.gamer.Player;
import java.util.HashMap;
import java.util.Map;

public class PlayerResult {

    private final Map<Player, Result> playerResult = new HashMap<>();

    public PlayerResult() {
    }

    public void add(Player player, Result result) {
        playerResult.put(player, result);
    }

    public Result findPlayerResult(Player player) {
        Result result = playerResult.get(player);
        validateResultExists(result, player);
        return result;
    }

    private void validateResultExists(Result result, Player player) {
        if (result == null) {
            String errorMessage = String.format("[ERROR] 해당 플레이어의 결과가 없습니다. (플레이어 : %s)", player.getName());
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
