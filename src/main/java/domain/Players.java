package domain;

import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = List.copyOf(players);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
