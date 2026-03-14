package domain;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class GameResult {
    private final Map<Player, Profit> playerProfits;
    private final Profit dealerProfit;

    private GameResult(Map<Player, Profit> playerProfits, Profit dealerProfit) {
        this.playerProfits = playerProfits;
        this.dealerProfit = dealerProfit;
    }

    public static GameResult calculate(Dealer dealer, Players players) {
        validateCalculatable(dealer);
        Map<Player, Profit> playerProfits = calculatePlayerResults(dealer, players);
        Profit dealerProfit = calculateDealerResult(playerProfits);
        return new GameResult(playerProfits, dealerProfit);
    }

    private static void validateCalculatable(Dealer dealer) {
        if (dealer.isDrawable()) {
            throw new IllegalStateException("딜러의 카드 합계가 16 이하이므로 아직 게임 결과를 계산할 수 없습니다.");
        }
    }

    private static Map<Player, Profit> calculatePlayerResults(Dealer dealer, Players players) {
        Map<Player, Profit> playerWinTieLossResults = new LinkedHashMap<>();
        for (Player player : players) {
            PlayerResult playerResult = PlayerResult.determinePlayerResult(dealer, player);
            double rate = playerResult.getReturnRate();
            long profit = player.calculateBettingProfit(rate);
            playerWinTieLossResults.put(player, new Profit(profit));
        }
        return playerWinTieLossResults;
    }

    private static Profit calculateDealerResult(Map<Player, Profit> playerProfits) {
        long playerBettingProfits = 0;
        for (Profit playerProfit : playerProfits.values()) {
            playerBettingProfits += playerProfit.profit();
        }
        return new Profit(playerBettingProfits * (-1));
    }

    public Map<Player, Profit> getPlayerProfits() {
        return Collections.unmodifiableMap(playerProfits);
    }

    public long getDealerProfit() {
        return dealerProfit.profit();
    }
}
