package participant;

import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public double sumProfits() {
        return players.stream()
                .mapToDouble(Player::calculateProfit)
                .sum();
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
