package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
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

    public List<ResultType> getDealerResult(Dealer dealer) {
        return gameResult.get(dealer);
    }

    public Map<Player, ResultType> getGamersResult(List<Gamer> gamers) {
        return gamers.stream()
                .collect(Collectors.toMap(gamer -> gamer, gamer -> gameResult.get(gamer).get(0)));
    }
}
