package domain;

import java.util.Collections;
import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private final Map<Player, Result> playerResults;
    private final Map<Result, Integer> dealerResult;

    private GameResult(Map<Player, Result> playerResults, Map<Result, Integer> dealerResult) {
        this.playerResults = playerResults;
        this.dealerResult = dealerResult;
    }

    public static GameResult calculate(Dealer dealer, Players players) {
        Map<Player, Result> playerResults = calculatePlayerResults(dealer, players);
        Map<Result, Integer> dealerResult = calculateDealerResult(playerResults);
        return new GameResult(playerResults, dealerResult);
    }

    private static Map<Player, Result> calculatePlayerResults(Dealer dealer, Players players) {
        boolean isDealerBust = dealer.isBust();
        int dealerScore = dealer.getCardsSum();
        Map<Player, Result> playerWinTieLossResults = new LinkedHashMap<>();
        for (Player player : players) {
            Result playerResult = calculatePlayerResult(isDealerBust, dealerScore, player);
            playerWinTieLossResults.put(player, playerResult);
        }
        return playerWinTieLossResults;
    }

    private static Result calculatePlayerResult(boolean isDealerBust, int dealerScore, Player player) {
        return Result.determinePlayerResult(
                isDealerBust,
                player.isBust(),
                dealerScore,
                player.getCardsSum()
        );
    }

    private static Map<Result, Integer> calculateDealerResult(Map<Player, Result> playerResults) {
        Map<Result, Integer> dealerResult = new EnumMap<>(Result.class);
        for (Result playerResult : playerResults.values()) {
            Result reversed = playerResult.reverse();
            dealerResult.put(reversed, dealerResult.getOrDefault(reversed, 0) + 1);
        }
        return dealerResult;
    }

    public Map<Player, Result> getPlayerResults() {
        return Collections.unmodifiableMap(playerResults);
    }

    public Map<Result, Integer> getDealerResult() {
        return Collections.unmodifiableMap(dealerResult);
    }
}
