package participant;

import java.util.Collections;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public Profit sumProfits() {
        return Profit.of(players.stream()
                .mapToInt(player -> player.calculateProfit().getAmount())
                .sum());
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
