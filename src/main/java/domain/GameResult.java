package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private final Map<Player, Long> playerResults;
    private final long dealerResult;

    private GameResult(Map<Player, Long> playerResults, long dealerResult) {
        this.playerResults = playerResults;
        this.dealerResult = dealerResult;
    }

    public static GameResult calculate(Dealer dealer, Players players) {
        validateCalculatable(dealer);
        Map<Player, Long> playerResults = calculatePlayerResults(dealer, players);
        long dealerResult = calculateDealerResult(playerResults);
        return new GameResult(playerResults, dealerResult);
    }

    private static void validateCalculatable(Dealer dealer) {
        if (dealer.isDrawable()) {
            throw new IllegalStateException("딜러의 카드 합계가 16 이하이므로 아직 게임 결과를 계산할 수 없습니다.");
        }
    }

    private static Map<Player, Long> calculatePlayerResults(Dealer dealer, Players players) {
        Map<Player, Long> playerWinTieLossResults = new LinkedHashMap<>();
        for (Player player : players) {
            PlayerResult playerResult = PlayerResult.determinePlayerResult(dealer, player);
            double rate = playerResult.getReturnRate();
            long profit = player.calculateBettingProfit(rate);
            playerWinTieLossResults.put(player, profit);
        }
        return playerWinTieLossResults;
    }

    private static long calculateDealerResult(Map<Player, Long> playerResults) {
        long playerBettingProfits = 0;
        for (long playerResult : playerResults.values()) {
            playerBettingProfits += playerResult;
        }
        return playerBettingProfits * (-1);
    }

    public Map<Player, Long> getPlayerResults() {
        return Collections.unmodifiableMap(playerResults);
    }

    public long getDealerResult() {
        return dealerResult;
    }
}
