package domain.participant;

import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    private Players(final List<Player> players) {
        this.players = players;
    }

    public static Players create(final List<Name> names) {
        final List<Player> players = names.stream()
                .map(Player::of)
                .collect(Collectors.toList());
        return new Players(players);
    }

    public List<Player> getPlayers() {
        return List.copyOf(players);
    }
}
