package blackjack.domain.result;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Player;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GameResult {

    private final Map<Player, ResultType> gamersResult;

    private GameResult(Map<Player, ResultType> gamersResult) {
        this.gamersResult = gamersResult;
    }

    public static GameResult of(Dealer dealer, List<Gamer> gamers) {
        Map<Player, ResultType> gameResult = judgeGameResult(dealer, gamers);
        return new GameResult(gameResult);
    }

    private static Map<Player, ResultType> judgeGameResult(Dealer dealer, List<Gamer> gamers) {
        Map<Player, ResultType> gamersResult = new LinkedHashMap<>();
        for (Gamer gamer : gamers) {
            gamersResult.put(gamer, ResultType.judgeGameResult(dealer, gamer));
        }
        return gamersResult;
    }

    public ResultType findResultByGamer(Gamer player) {
        return gamersResult.get(player);
    }

    public List<ResultType> getDealerResult() {
        return gamersResult.keySet()
            .stream()
            .map(player -> gamersResult.get(player).reverse())
            .collect(Collectors.toList());
    }

    public Map<Player, ResultType> getGamersResult() {
        return gamersResult.keySet()
            .stream()
            .filter(player -> !player.isDealer())
            .collect(Collectors.toMap(key -> key, key -> gamersResult.get(key)));
    }
}
