package domain;

import java.util.Iterator;
import java.util.List;

public class Players implements Iterable<Player> {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = List.copyOf(players);
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }

    public boolean areAllBust() {
        return players.stream().allMatch(Player::isBust);
    }
}
