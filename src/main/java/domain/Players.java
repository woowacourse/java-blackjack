package domain;

import java.util.ArrayList;
import java.util.List;

public class Players {

    private final List<Player> players;

    public Players(List<Player> playersInput) {
        this.players = playersInput;
    }

    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }
}
