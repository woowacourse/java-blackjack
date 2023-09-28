package model;

import java.util.List;

public class Player {

    private final Name name;
    private final Cards cards;

    private Player(final Name name, final Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public static Player from(final Name name) {
        return new Player(name, Cards.createPlayerCards());
    }

    public void selectCardsFromDeck(List<Card> card) {
        cards.addCards(card);
    }

    public Name getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
