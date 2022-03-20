package blackjack.domain.result;

import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;

public class BettingResult {

    private final double dealerRevenue;
    private final Map<Gamer, Double> playerRevenue;

    public BettingResult(final double dealerRevenue, final Map<Player, Double> playerRevenue) {
        this.dealerRevenue = dealerRevenue;
        this.playerRevenue = new LinkedHashMap<>(playerRevenue);
    }

    public double getDealerRevenue() {
        return dealerRevenue;
    }

    public Map<Gamer, Double> getPlayerRevenue() {
        return playerRevenue;
    }
}
