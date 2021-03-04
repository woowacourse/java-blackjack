package blackjack.domain;

import blackjack.utils.CardDeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(String s, CardDeck cardDeck) {
        players = new ArrayList<>();
        String[] names = s.split(",");
        for (String name : names) {
            players.add(new Player(name, cardDeck));
        }
    }

    public List<Player> values() {
        return Collections.unmodifiableList(players);
    }
}
