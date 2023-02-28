package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players of(final List<String> names) {
        List<Player> players = names.stream()
            .map(Player::new)
            .collect(Collectors.toList());
        return new Players(players);
    }
}
