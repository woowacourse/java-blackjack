package model;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

import java.util.List;

public class Players {

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<String> playerNames) {
        return playerNames.stream()
            .map(Player::new)
            .collect(collectingAndThen(toList(), Players::new));
    }
}
