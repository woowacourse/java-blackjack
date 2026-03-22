package domain.participant;

import java.util.List;
import java.util.function.Consumer;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = List.copyOf(players);
    }

    public int getSize() {
        return players.size();
    }

    public void forEachPlayer(final Consumer<Player> action) {
        players.forEach(action);
    }

    public int getTotalProfit() {
        return players.stream()
                .mapToInt(Player::getBalance)
                .sum();
    }
}
