package blackjack.domain.state;

import blackjack.domain.card.Card;

public abstract class Finished extends Ready {

    protected static final double WIN_RATE = 1;
    protected static final double BLACKJACK_WIN_RATE = 1.5;
    protected static final double DRAW_RATE = 0;
    protected static final double LOSE_RATE = -1;

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
