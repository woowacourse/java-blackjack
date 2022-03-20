package blackjack.domain.result;

import java.util.LinkedHashMap;
import java.util.Map;

import blackjack.domain.player.Guest;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;

public class Profits {

    private final Players players;

    private Profits(Players players) {
        this.players = players;
    }

    public static Profits of(Players players) {
        return new Profits(players);
    }

    private Map<Player, Profit> competeDealerWithGuest() {
        Map<Player, Profit> playersProfit = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            calcProfitIfGuest(playersProfit, player);
        }
        calcDealer(playersProfit);
        return playersProfit;
    }

    private void calcProfitIfGuest(Map<Player, Profit> playersProfit, Player guest) {
        if (guest.isDealer()) {
            return;
        }
        calcEachGuest(playersProfit,(Guest) guest);
    }

    private void calcEachGuest(Map<Player, Profit> playersProfit, Guest guest) {
        Player dealer = players.getDealer();
        Match match = guest.getState().matchResult(dealer);
        Profit profit = Profit.of(match.getRatio(), guest.getBetMoney());
        playersProfit.put(guest, profit);
    }

    private void calcDealer(Map<Player, Profit> playersProfit) {
        Player dealer = players.getDealer();
        double profit = playersProfit.keySet()
                .stream()
                .mapToDouble(player -> playersProfit.get(player).getValue())
                .sum();
        playersProfit.put(dealer, Profit.of(-profit));
    }

    public Map<Player, Profit> getPlayersProfit() {
        return competeDealerWithGuest();
    }
}
