package domain.participant;

import java.util.List;
import java.util.function.Consumer;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = List.copyOf(players);
    }

    public void forEach(final Consumer<Player> action) {
        players.forEach(action);
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
