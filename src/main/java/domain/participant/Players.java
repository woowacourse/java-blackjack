package domain.participant;

import java.util.ArrayList;
import java.util.List;

public record Players(List<Player> players) {

    public Players() {
        this(List.of());
    }

    public Players(List<Player> players) {
        this.players = List.copyOf(players);
    }

    public Players addPlayer(Player player) {
        List<Player> players = new ArrayList<>(this.players);
        Players.this.players.add(player);
        return new Players(players);
    }

    public int size() {
        return players.size();
    }
}
