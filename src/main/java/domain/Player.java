package domain;

import java.util.List;

public abstract class Player {

    private final String name;
    private final Cards cards;

    Player(final String name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public List<Card> displayCards() {
        return cards.displayCards();
    }

    public void takeCard(final Card card) {
        cards.takeCard(card);
    }

    public int getScore() {
        return cards.getScore();
    }

    public String getName() {
        return name;
    }
}
