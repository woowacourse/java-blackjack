package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {

    private final Map<Player, Profit> profitResult;

    public GameResult(Map<Player, Profit> profitResult) {
        this.profitResult = profitResult;
    }

    public static GameResult of(Dealer dealer, Players players) {
        return new GameResult(makeGameResult(dealer, players));
    }

    private static Map<Player, Profit> makeGameResult(Dealer dealer, Players players) {
        Map<Player, Profit> gameResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            gameResult.put(player, calculateProfit(dealer, player));
        }
        return gameResult;
    }

    private static Profit calculateProfit(Dealer dealer, Player player) {
        Result result = ResultJudge.makeResult(player, dealer);
        double profit = Result.calculateProfit(result, player.getBat());
        return Profit.from(profit);
    }

    public Double getSumOfProfit() {
        return profitResult.values()
                .stream()
                .mapToDouble(Profit::getProfit)
                .sum();
    }

    public Map<Player, Profit> getProfitResult() {
        return profitResult;
    }
}
