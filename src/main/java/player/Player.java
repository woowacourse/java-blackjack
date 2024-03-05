package player;

import card.Deck;
import card.Hand;

public class Player {

    protected final Hand hand;

    public Player() {
        this.hand = new Hand();
    }

    public void drawCard(Deck deck) {
        hand.addCard(deck.draw());
    }
}
