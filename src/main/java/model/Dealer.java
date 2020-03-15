package model;

import static controller.BlackJackGame.HIT_BOUNDARY;

public class Dealer extends User {
    public static final int ZERO = 0;

    public Dealer(Deck deck) {
        super(deck);
    }

    public String toStringCardHandFirst() {
        return cardHand.getCards().get(ZERO).toString();
    }

    public boolean isHitBound() {
        return getScore() <= HIT_BOUNDARY;
    }
}
