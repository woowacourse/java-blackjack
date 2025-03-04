package domain;

import java.util.List;

public class Player {

    private final String name;
    private final Cards ownedCards;

    private Player(final String name) {
        this.name = name;
        this.ownedCards = Cards.of();
    }

    public static Player of(final String name) {
        return new Player(name);
    }

    public void receive(final Card card) {
        ownedCards.add(card);
    }

    public List<Card> getOwnedCards() {
        return ownedCards.getCards();
    }

    public String getName() {
        return name;
    }
}
