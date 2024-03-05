package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final List<String> names) {
        final List<Player> players = new ArrayList<>();
        for (String name : names) {
            players.add(new Player(new Name(name)));
        }
        return new Players(players);
    }
}
