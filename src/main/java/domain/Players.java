package domain;

import java.util.ArrayList;
import java.util.List;

public class Players {

    List<Player> players;

    public Players() {
        this.players = new ArrayList<>();
    }

    public void add(Player player) {
        players.add(player);
    }
}
