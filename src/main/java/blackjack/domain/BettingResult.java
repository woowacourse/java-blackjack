package blackjack.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;

public class BettingResult {

    private final int dealerRevenue;
    private final Map<Gamer, Integer> playerRevenue;

    public BettingResult(final int dealerRevenue, final Map<Player, Integer> playerRevenue) {
        this.dealerRevenue = dealerRevenue;
        this.playerRevenue = new LinkedHashMap<>(playerRevenue);
    }

    public int getDealerRevenue() {
        return dealerRevenue;
    }

    public Map<Gamer, Integer> getPlayerRevenue() {
        return playerRevenue;
    }
}
