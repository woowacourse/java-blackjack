package blackjack.model;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players from(final List<String> names) {
        return names.stream()
                .map(Player::new)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Players::new));
    }
}
