package model.state;

import model.card.Card;
import model.card.CardHand;

public abstract class Finished extends Started {
    public Finished(final CardHand cardHand) {
        super(cardHand);
    }

    public abstract double earningRate();

    public int profit(final int bettingAmount) {
        return (int) (bettingAmount * earningRate());
    }

    @Override
    public State receiveCard(final Card card) {
        throw new IllegalArgumentException();
    }

    @Override
    public State stay() {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
