package blackjack.model.gameRule;

import blackjack.model.gamer.Player;
import java.util.HashMap;
import java.util.Map;

public class PlayersResult {

    private final Map<Player, Result> playerResult = new HashMap<>();

    protected PlayersResult() {
    }

    public void add(Player player, Result result) {
        playerResult.put(player, result);
    }

    public Result findPlayerResult(Player player) {
        Result result = playerResult.get(player);
        validatePlayerNull(result, player);
        return result;
    }

    private void validatePlayerNull(Result result, Player player) {
        if (result == null) {
            String errorMessage = String.format("[ERROR] 해당 플레이어의 결과가 없습니다. (플레이어 : %s)", player.name());
            throw new IllegalArgumentException(errorMessage);
        }
    }
}
