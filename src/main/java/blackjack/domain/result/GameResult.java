package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {
    private final Map<Player, List<ResultType>> gameResult;

    private GameResult(Map<Player, List<ResultType>> gameResult) {
        this.gameResult = gameResult;
    }

    public static GameResult of(Dealer dealer, List<Gamer> gamers) {
        Map<Player, List<ResultType>> result = new LinkedHashMap<>();
        result.put(dealer, new ArrayList<>());
        for (Gamer gamer : gamers) {
            result.put(gamer, new ArrayList<>());
            Map<Player, ResultType> resultPerGamer = ResultType.judgeGameResult(dealer, gamer);
            result.get(gamer).add(resultPerGamer.get(gamer));
            result.get(dealer).add(resultPerGamer.get(dealer));
        }
        return new GameResult(result);
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
