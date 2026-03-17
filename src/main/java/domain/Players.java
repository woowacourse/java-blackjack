package domain;

import java.util.Iterator;
import java.util.List;

public class Players implements Iterable<Player> {
    private List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public void addAdditionalCard(Player player, Card card) {
        player.add(card);
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
