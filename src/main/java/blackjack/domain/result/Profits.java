package blackjack.domain.result;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class Profits {

    private final Map<Player, Profit> playersProfit;

    public Profits(Players players) {
        this.playersProfit = competeDealerWithGuest(players);
    }

    private Map<Player, Profit> competeDealerWithGuest(Players players) {
        Map<Player, Profit> profits = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            calcProfitIfGuest(profits, players, player);
        }
        calcDealer(profits, players);
        return profits;
    }

    private void calcProfitIfGuest(Map<Player, Profit> profits, Players players, Player guest) {
        if (guest.isDealer()) {
            return;
        }
        calcEachGuest(profits, players, (Guest) guest);
    }

    private void calcEachGuest(Map<Player, Profit> profits, Players players, Guest guest) {
        Player dealer = players.getDealer();
        Match match = Match.findWinner(guest, dealer);
        Profit profit = Profit.of(match, guest.getBetMoney());
        profits.put(guest, profit);
    }

    private void calcDealer(Map<Player, Profit> profits, Players players) {
        Player dealer = players.getDealer();
        double profit = profits.keySet()
                .stream()
                .mapToDouble(player -> profits.get(player).getValue())
                .sum();
        profits.put(dealer, new Profit(-profit));
    }

    public Map<Player, Profit> getPlayersProfit() {
        return Collections.unmodifiableMap(playersProfit);
    }
}
