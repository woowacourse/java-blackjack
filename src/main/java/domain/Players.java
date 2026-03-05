package domain;

import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public int getSize() {
        return players.size();
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }
}
