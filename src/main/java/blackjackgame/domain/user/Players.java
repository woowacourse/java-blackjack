package blackjackgame.domain.user;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Players {
    private final List<Player> players;

    public Players(Names names, List<Bet> bets) {
        players = new ArrayList<>();
        for (int playerCount = 0; playerCount < bets.size(); playerCount++) {
            Name name = names.getNames().get(playerCount);
            Bet bet = bets.get(playerCount);

            players.add(new Player(name, bet));
        }
    }

    public Map<Player, Profit> getPlayerProfits() {
        Map<Player, Profit> profitByPlayer = new LinkedHashMap<>();
        for (Player player : players) {
            profitByPlayer.put(player, player.getProfit());
        }
        return profitByPlayer;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
