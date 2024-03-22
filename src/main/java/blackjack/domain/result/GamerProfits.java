package blackjack.domain.result;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;

import java.util.HashMap;
import java.util.Map;

public class GamerProfits {
    private final Map<Gamer, Integer> gamerProfits;

    public GamerProfits() {
        this(new HashMap<>());
    }

    public GamerProfits(final Map<Gamer, Integer> gamerProfits) {
        this.gamerProfits = new HashMap<>(gamerProfits);
    }

    public void addPlayersProfit(final Player player, final int profit) {
        gamerProfits.put(player, profit);

    }

    public void addDealerProfit(final Dealer dealer, final int profit) {
        gamerProfits.put(dealer, profit);
    }

    public int getProfit(final Gamer gamer) {
        return gamerProfits.get(gamer);
    }

    @Override
    public String toString() {
        return "GamerProfits{" +
                "gamerProfits=" + gamerProfits +
                '}';
    }
}
