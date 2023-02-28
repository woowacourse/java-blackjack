package domain;

import java.util.List;

public class Player {

    private final Hand hand;

    public Player() {
        hand = new Hand();
    }

    public void drawCard() {
        hand.addCard(Deck.popCard());
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}
