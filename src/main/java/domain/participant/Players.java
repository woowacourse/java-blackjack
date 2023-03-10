package domain.participant;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static Players of(Player... players) {
        List<Player> newPlayers = new ArrayList<>(Arrays.asList(players));
        return new Players(newPlayers);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
