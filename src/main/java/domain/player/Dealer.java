package domain.player;

import domain.area.CardArea;

public class Dealer extends Player {

    private static final int DEALER_LIMIT_SCORE = 16;

    public Dealer(final Name name, final CardArea cardArea) {
        super(name, cardArea);
    }

    @Override
    public boolean canHit() {
        return cardArea.calculate() <= DEALER_LIMIT_SCORE;
    }
}
