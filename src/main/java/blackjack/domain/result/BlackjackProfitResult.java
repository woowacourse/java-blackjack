package blackjack.domain.result;

import blackjack.domain.participant.Player;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackProfitResult {

    private final List<Player> players;

    public BlackjackProfitResult(List<Player> players) {
        this.players = players;
    }

    public Double calculateDealerProfit(Map<Player, Double> playersProfitResult) {
        double dealerProfit = 0;
        for (Double blackjackProfit : playersProfitResult.values()) {
            dealerProfit -= blackjackProfit;
        }
        return dealerProfit;
    }

    public Map<Player, Double> calculatePlayersProfit(Map<Player, BlackjackMatch> result) {
        final Map<Player, Double> playersProfitResult = new LinkedHashMap<>();
        for (Player player : players) {
            final BlackjackMatch match = result.get(player);
            final double profit = player.calculateProfit(match);
            playersProfitResult.put(player, profit);
        }
        return playersProfitResult;
    }
}
