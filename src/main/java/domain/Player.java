package domain;

import java.util.List;

public class Player {
    private final Name name;
    private final Hand hand;

    public Player(final String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public void drawCard(Deck deck) {
        hand.addCard(deck.popCard());
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public String getName() {
        return name.getName();
    }
}
