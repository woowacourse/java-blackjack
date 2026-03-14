package domain;

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
        validateCalculatable(dealer);
        Map<Player, Result> playerResults = calculatePlayerResults(dealer, players);
        Map<Result, Integer> dealerResult = calculateDealerResult(playerResults);
        return new GameResult(playerResults, dealerResult);
    }

    private static void validateCalculatable(Dealer dealer) {
        if (dealer.isDrawable()) {
            throw new IllegalStateException("딜러의 카드 합계가 16 이하이므로 아직 게임 결과를 계산할 수 없습니다.");
        }
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
        return Map.copyOf(playerResults);
    }

    public Map<Result, Integer> getDealerResult() {
        return Map.copyOf(dealerResult);
    }
}
