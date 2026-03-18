package domain;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Players implements Iterable<Player> {
    private List<Player> players;

    public Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public int getTotalBettingScore() {
        return players.stream()
                .mapToInt(Player::getBettingScore)
                .sum();
    }

    @Override
    public Iterator<Player> iterator() {
        return players.iterator();
    }

    public boolean isAllPlayerBurst() {
        if (players.isEmpty()) {
            return false;
        }
        return players.stream().allMatch(Player::isBust);
    }

}
