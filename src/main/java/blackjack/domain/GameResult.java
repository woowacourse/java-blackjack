package blackjack.domain;

import blackjack.domain.participant.BetMoney;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private final Map<Player, Result> gameResult;

    private GameResult(Map<Player, Result> gameResult) {
        this.gameResult = gameResult;
    }

    public static GameResult of(Dealer dealer, Players players) {
        return new GameResult(makeGameResult(dealer, players));
    }

    private static Map<Player, Result> makeGameResult(Dealer dealer, Players players) {
        Map<Player, Result> gameResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            gameResult.put(player, Result.of(dealer, player));
        }
        return gameResult;
    }

    public long findTargetResultCount(Result targetResult) {
        return gameResult.values()
                .stream()
                .filter(result -> result == targetResult)
                .count();
    }

    public long calculatePlayerProfit(Player player, BetMoney betMoney) {
        Result playerResult = gameResult.get(player);
        return playerResult.findBetProfit(betMoney);
    }

    public long calculateDealerProfit(BetManager betManager) {
        long totalPlayerProfit = 0;
        for (Player player : gameResult.keySet()) {
            BetMoney betMoney = betManager.findPlayerBetMoney(player);
            totalPlayerProfit += calculatePlayerProfit(player, betMoney);
        }
        return -totalPlayerProfit;
    }

    public Map<Player, Result> getGameResult() {
        return gameResult;
    }
}
