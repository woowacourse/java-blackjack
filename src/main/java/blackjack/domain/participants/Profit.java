package blackjack.domain.participants;

import java.util.Map;

public record Profit(
        double dealerProfit,
        Map<Player, Double> playerProfit
) {
    public Profit(double dealerProfit, Map<Player, Double> playerProfit) {
        this.dealerProfit = dealerProfit;
        this.playerProfit = playerProfit;
    }
}
