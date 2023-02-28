package domain;

import static java.util.stream.Collectors.collectingAndThen;

import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players of(final List<String> names) {
        return names.stream()
            .map(Player::new)
            .collect(collectingAndThen(Collectors.toList(), Players::new));
    }
}
