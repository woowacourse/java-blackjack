package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParticipantProfit {

    private final Map<String, Double> playerValues;
    private final double dealerValue;

    private ParticipantProfit(Map<String, Double> playerValues, double dealerValue) {
        this.playerValues = playerValues;
        this.dealerValue = dealerValue;
    }

    public static ParticipantProfit create(Dealer dealer, List<Player> players) {
        Map<String, Double> playerValues = new HashMap<>();

        for (Player player : players) {
            double profit = player.findProfit(dealer);
            playerValues.put(player.getName(), profit);
        }

        return new ParticipantProfit(playerValues, calculateDealerProfit(playerValues));
    }

    private static double calculateDealerProfit(Map<String, Double> playerValues) {
        return playerValues.values().stream()
                .mapToDouble(Double::doubleValue)
                .sum() * -1;
    }

    public Map<String, Double> getPlayerValues() {
        return playerValues;
    }

    public double getDealerValue() {
        return dealerValue;
    }
}
