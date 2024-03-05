package player;

import card.Deck;
import card.Hand;

public class Player {

    private static final int MAX_DRAWABLE_SCORE = 16;

    private final Name name;
    protected final Hand hand;

    Player(String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public void drawCard(Deck deck) {
        hand.addCard(deck.draw());
    }

    public boolean hasDrawableScore() {
        return hand.calculateScore() <= MAX_DRAWABLE_SCORE;
    }
}
