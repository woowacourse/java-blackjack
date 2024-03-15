package model.player;

import model.card.Cards;

public class Dealer extends User {

    private static final int NUMBER_THRESHOLD = 16;

    public Dealer(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isHit() {
        return calculateScore() <= NUMBER_THRESHOLD;
    }
}
