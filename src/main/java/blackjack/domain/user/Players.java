package blackjack.domain.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(List<String> playerNames) {
        this.players = Collections.unmodifiableList(playerNames.stream()
            .map(Player::new)
            .collect(Collectors.toList()));
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<String> getPlayerNames() {
        return Collections.unmodifiableList(players.stream()
            .map(User::getName)
            .collect(Collectors.toList()));
    }
}
