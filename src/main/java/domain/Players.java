package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(PlayerNames playerNames) {
        return new Players(playerNames.getNames()
                .stream()
                .map(Player::from)
                .collect(Collectors.toList()));
    }

    public List<Player> getPlayers() {
        return players;
    }
}
