package domain.user;

import java.util.List;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<String> nameValues) {
        this.players = nameValues.stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public List<Player> getPlayers() {
        return this.players;
    }
}
