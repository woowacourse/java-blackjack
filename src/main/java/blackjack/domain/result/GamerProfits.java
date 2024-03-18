package blackjack.domain.result;

import blackjack.domain.betting.Bettings;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;

import java.util.HashMap;
import java.util.Map;

public class GamerProfits {
    private final Map<Gamer, Integer> gamerProfits;

    public GamerProfits() {
        this.gamerProfits = new HashMap<>();
    }

    public GamerProfits(final Map<Gamer, Integer> gamerProfits) {
        this.gamerProfits = new HashMap<>(gamerProfits);
    }

    public void addPlayersProfit(final Players players, final GameResults gameResults, final Bettings bettings) {
        for (final Player player : players.getPlayers()) {
            gamerProfits.put(player, gameResults.calculatePlayerProfit(player, bettings));
        }
    }

    public void addDealerProfit(final Dealer dealer, final GameResults gameResults, final Bettings bettings) {
        gamerProfits.put(dealer, gameResults.calculateDealerProfit(bettings));
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
