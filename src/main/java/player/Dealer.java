package player;

import card.Card;

public class Dealer {
    private final Hand hand = new Hand();

    public void hit(Card card) {
        hand.add(card);
    }
}
