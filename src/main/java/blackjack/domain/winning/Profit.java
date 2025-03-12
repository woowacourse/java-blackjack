package blackjack.domain.winning;

import blackjack.domain.participants.Player;
import java.util.Map;

public class Profit {
    private final int dealerProfit;
    private final Map<Player, Integer> playerProfit;

    public Profit(int dealerProfit, Map<Player, Integer> playerProfit) {
        this.dealerProfit = dealerProfit;
        this.playerProfit = playerProfit;
    }
}
