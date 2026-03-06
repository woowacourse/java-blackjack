package domain;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Players implements Iterable<Player> {
    private List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }

    public boolean isAllPlayerBurst() {
        return players.stream().allMatch(Player::isBust);
    }
}
