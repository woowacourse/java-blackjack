package blackjack.domain.participants;

import java.util.Map;

public record Profit(
        int dealerProfit,
        Map<Player, Integer> playerProfit
) {
    public Profit(int dealerProfit, Map<Player, Integer> playerProfit) {
        this.dealerProfit = dealerProfit;
        this.playerProfit = playerProfit;
    }
}
