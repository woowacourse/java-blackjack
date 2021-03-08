package blackjack.domain;

import blackjack.utils.CardDeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Players {
    private final List<Player> players;

    public Players(String namesInput, CardDeck cardDeck) {
        players = new ArrayList<>();
        validate(namesInput);
        String[] names = namesInput.split(",");
        for (String name : names) {
            players.add(new Player(name, cardDeck));
        }
    }

    private void validate(String namesInput) {
        if (namesInput == null || namesInput.isEmpty()) {
            throw new IllegalArgumentException();
        }
    }

    public List<Player> values() {
        return Collections.unmodifiableList(players);
    }
}
