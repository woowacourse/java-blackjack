package domain.participant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(Player... players) {
        List<Player> newPlayers = new ArrayList<>(Arrays.asList(players));
        return new Players(newPlayers);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
