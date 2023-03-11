package blackjackgame.domain.user;

import java.util.ArrayList;
import java.util.List;

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

    public List<Player> getPlayers() {
        return players;
    }
}
