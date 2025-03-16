package model.state;

import model.card.CardHand;

public final class Bust extends Finished {
    public Bust(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public double earningRate() {
        return 0;
    }
}
