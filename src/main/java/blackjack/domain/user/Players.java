package blackjack.domain.user;

import java.util.Collections;
import java.util.List;

public class Players {
    private List<Player> players;

    public Players(List<Player> players) {
        this.players = players;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(this.players);
    }
}
