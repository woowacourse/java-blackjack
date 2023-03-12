package blackjack.domain.user;

import java.util.List;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public static Players from(Names names) {
        List<Player> players = names.getNames().stream()
                .map(Player::new)
                .collect(Collectors.toUnmodifiableList());
        return new Players(players);
    }
}
