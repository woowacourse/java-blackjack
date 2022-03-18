package blackjack.domain.state;

import blackjack.domain.card.Card;

public abstract class Finished extends Ready {

    Finished() {
    }

    @Override
    public State draw(Card card) {
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
