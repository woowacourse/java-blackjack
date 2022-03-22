package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParticipantProfit {

    private final Map<String, Double> playerProfit;
    private final double dealerProfit;

    private ParticipantProfit(Map<String, Double> playerProfit, double dealerProfit) {
        this.playerProfit = playerProfit;
        this.dealerProfit = dealerProfit;
    }

    public static ParticipantProfit create(Dealer dealer, List<Player> players) {
        Map<String, Double> playerProfit = new HashMap<>();

        for (Player player : players) {
            double profit = player.findProfit(dealer);
            playerProfit.put(player.getName(), profit);
        }

        return new ParticipantProfit(playerProfit, calculateDealerProfit(playerProfit));
    }

    private static double calculateDealerProfit(Map<String, Double> playerProfit) {
        return playerProfit.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum() * -1;
    }

    public Map<String, Double> getPlayerProfit() {
        return playerProfit;
    }

    public double getDealerProfit() {
        return dealerProfit;
    }
}
