package state.finished;

import card.Card;
import card.CardHand;
import state.Started;
import state.State;

public abstract class Finished extends Started {
    public Finished(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public State receiveCard(final Card card) {
        throw new IllegalStateException();
    }

    @Override
    public State stay() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
