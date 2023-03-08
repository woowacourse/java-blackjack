package blackjack.domain.participant;

import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;

    protected Players(final List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }

    public int size() {
        return players.size();
    }
}
