package domain.user;

import domain.Names;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    private final List<Player> players = new ArrayList<>();

    public Players(Names names) {
        for (String name : names.get()) {
            players.add(new Player(name));
        }
    }

    public List<Player> get() {
        return Collections.unmodifiableList(players);
    }
}
