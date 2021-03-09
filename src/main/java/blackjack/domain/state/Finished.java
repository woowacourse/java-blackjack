package blackjack.domain.state;

import blackjack.domain.Hand;

public abstract class Finished implements State {
    @Override
    public State draw(Hand hand) {
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
