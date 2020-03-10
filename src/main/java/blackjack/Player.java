package blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> cards;

    private Player(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public static Player of(String name) {
        return new Player(name);
    }

    public void append(Card card) {
        cards.add(card);
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }
}
