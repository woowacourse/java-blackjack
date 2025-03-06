package model;

import java.util.Map;

public class Players {

    private final Map<String, Cards> players;

    public Players(final Map<String, Cards> players) {
        this.players = players;
    }

    public void addCardByName(final String name, final Card card) {
        findCardsByName(name).addCard(card);
        // TODO : 테스트 해야함 !
    }

    private Cards findCardsByName(final String name) {
        if (!players.containsKey(name)) {
            throw new IllegalArgumentException();
        }
        return players.get(name);
    }
}
