package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {

    private final String name;
    private final List<Card> cards;

    private Player(final String name) {
        this.name = name;
        cards = new ArrayList<>();
    }

    public static Player of(final String name) {
        return new Player(name);
    }

    public void receive(final Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
