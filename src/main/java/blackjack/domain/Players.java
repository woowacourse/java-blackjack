package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
}
