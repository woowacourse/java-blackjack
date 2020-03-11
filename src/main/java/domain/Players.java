package domain;

import java.util.Iterator;
import java.util.List;

public class Players implements Iterable<Player> {
    private List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }
}
