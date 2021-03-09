package blackjack.domain.result;

import blackjack.domain.player.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {

    private final Map<Player, List<ResultType>> gameResult;

    private GameResult(Map<Player, List<ResultType>> gameResult) {
        this.gameResult = gameResult;
    }

    public static GameResult of(Map<Player, List<ResultType>> gameResult) {
        return new GameResult(gameResult);
    }

    public List<ResultType> findByPlayer(Player player) {
        return gameResult.get(player);
    }

    public List<ResultType> getDealerResult() {
        Player dealer = gameResult.keySet()
            .stream()
            .filter(Player::isDealer)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("[ERROR] 게임 결과에 딜러가 존재하지 않습니다."));
        return gameResult.get(dealer);
    }

    public Map<Player, ResultType> getGamersResult() {
        return gameResult.keySet()
            .stream()
            .filter(player -> !player.isDealer())
            .collect(Collectors.toMap(key -> key, key -> gameResult.get(key).get(0)));
    }
}
