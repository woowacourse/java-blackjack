package domain.user;

import java.util.Collections;
import java.util.List;

import static domain.util.NullValidator.validateNull;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        validateNull(players);
        this.players = players;
    }

    public List<Player> get() {
        return Collections.unmodifiableList(players);
    }
}
