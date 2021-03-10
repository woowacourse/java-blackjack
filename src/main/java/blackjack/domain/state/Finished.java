package blackjack.domain.state;

import blackjack.domain.Hand;

public abstract class Finished extends Started {
    @Override
    public State update(Hand hand) {
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
