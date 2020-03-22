package domains.result;

import domains.result.strategy.*;
import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameResultFactory {
    private static List<ResultStrategy> strategies = Arrays.asList(
            new PlayerBlackJackStrategy(),
            new PlayerWinStrategy(),
            new PlayerDrawStrategy(),
            new PlayerLoseStrategy());

    public static GameResult create(Players players, Dealer dealer) {
        Map<Player, ResultType> playerResult = new HashMap<>();

        for (Player player : players) {
            ResultStrategy strategy = getStrategy(player, dealer);
            ResultType resultType = strategy.getResult(player, dealer);
            playerResult.put(player, resultType);
        }

        return new GameResult(playerResult);
    }

    private static ResultStrategy getStrategy(Player player, Dealer dealer) {
        return strategies.stream()
                .filter(strategy -> strategy.checkResult(player, dealer))
                .findAny()
                .orElseThrow(InvalidResultStrategy::new);
    }
}
