package blackjack.domain.result;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class Profits {

    private final Map<Player, Profit> playersProfit;

    private Profits(Map<Player, Profit> playersProfit) {
        this.playersProfit = playersProfit;
    }

    public static Profits of() {
        return new Profits(new LinkedHashMap<>());
    }

    public void competeDealerWithGuest(Players players) {
        for (Player player : players.getPlayers()) {
            calcProfitIfGuest(players, player);
        }
        calcDealer(players);
    }

    private void calcProfitIfGuest(Players players, Player guest) {
        if (guest.isDealer()) {
            return;
        }
        calcEachGuest(players, (Guest) guest);
    }

    private void calcEachGuest(Players players, Guest guest) {
        Player dealer = players.getDealer();
        Match match = guest.getState().matchResult(dealer);
        Profit profit = Profit.of(match.getRatio(), guest.getBetMoney());
        playersProfit.put(guest, profit);
    }

    private void calcDealer(Players players) {
        Player dealer = players.getDealer();
        double profit = playersProfit.keySet()
                .stream()
                .mapToDouble(player -> playersProfit.get(player).getValue())
                .sum();
        playersProfit.put(dealer, Profit.of(-profit));
    }

    public Map<Player, Profit> getPlayersProfit() {
        return Collections.unmodifiableMap(playersProfit);
    }
}
