package domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(String[] names) {
        return new Players(Arrays.stream(names).map(Player::new).collect(Collectors.toList()));
    }

    public List<Player> getPlayers() {
        String s = "qwrqwr";
        return players;
    }
}
