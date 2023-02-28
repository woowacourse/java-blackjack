package domain;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> names) {
        List<Player> players = new ArrayList<>();
        names.forEach(name -> players.add(new Player(name)));
        return new Players(players);
    }
}
