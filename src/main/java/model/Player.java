package model;

public class Player {

    private final String name;
    private final Cards cards;

    public Player(final String name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public void addCard(final Card card) {
        cards.addCard(card);
    }
}
