package blackjack;

import java.util.ArrayList;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(String s) {
        players = new ArrayList<>();
        String[] names = s.split(",");
        for (String name : names) {
            players.add(new Player(name));
        }
    }
}
