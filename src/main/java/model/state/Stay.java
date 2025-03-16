package model.state;

import model.card.CardHand;

public final class Stay extends Finished {
    public Stay(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public double earningRate() {
        return 1;
    }
}
