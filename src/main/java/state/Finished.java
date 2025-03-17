package state;

import card.Card;
import card.CardHand;

public abstract class Finished extends Started {
    public Finished(final CardHand cardHand) {
        super(cardHand);
    }

    public abstract double earningRate();

//    public Bet profit(final Bet bettingAmount) {
//        return bettingAmount.multiply(earningRate());
//    }

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
