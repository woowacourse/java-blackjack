package model.player;

import model.card.Card;

import java.util.List;

public class Dealer extends User {

    private static final int NUMBER_THRESHOLD = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer(List<Card> cards) {
        super(DEALER_NAME, cards);
    }

    public boolean isHit() {
        return calculateScore() <= NUMBER_THRESHOLD;
    }
}
