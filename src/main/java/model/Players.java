package model;

import java.util.Map;
import java.util.Set;
import model.cards.Cards;

public class Players {

    private final Map<String, Cards> players;

    public Players(final Map<String, Cards> players) {
        this.players = players;
    }

    public Cards findCardsByName(final String name) {
        if (!players.containsKey(name)) {
            throw new IllegalArgumentException();
        }
        return players.get(name);
    }

    public Set<String> getNames() {
        return players.keySet();
    }
}
