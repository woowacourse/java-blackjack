package blackjack.domain.gamer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(List<Player> players) {
        this.players = new ArrayList<>(players);
    }

    public List<Player> getUnmodifiableList() {
        return Collections.unmodifiableList(players);
    }
}
