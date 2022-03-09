package domain.participant;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private static final String DELIMITER = ",";

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(String names) {
        List<Player> players =  Arrays.stream(names.split(DELIMITER))
            .map(String::trim)
            .map(Player::new)
            .collect(Collectors.toList());

        return new Players(players);
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
