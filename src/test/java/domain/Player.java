package domain;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final List<Card> cards;

    public Player() {
        cards = new ArrayList<>();
    }

    public void add(final Card card) {
        cards.add(card);
    }
}
