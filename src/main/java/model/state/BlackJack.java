package model.state;

import model.card.CardHand;

public final class BlackJack extends Finished {
    public BlackJack(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public double earningRate() {
        return 1.5;
    }
}
