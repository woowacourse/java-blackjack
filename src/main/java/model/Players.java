package model;

import exception.IllegalBlackjackStateException;
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
            throw new IllegalBlackjackStateException("존재하지 않는 플레이어입니다.");
        }
        return players.get(name);
    }

    public Set<String> getNames() {
        return players.keySet();
    }
}
