package blackjack.domain.participants;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private static final String SPLIT_DELIMITER = ",";

    List<Player> players;

    public Players(String names) {
        this.players = splitNames(names);
    }

    private List<Player> splitNames(String names) {
        return Arrays.stream(names
                .split(SPLIT_DELIMITER))
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return players;
    }
}
