package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players createPlayersByNames(List<String> names) {
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            Player player = new Player(name);
            players.add(player);
        }
        return new Players(players);
    }

    public int size() {
        return players.size();
    }
}
