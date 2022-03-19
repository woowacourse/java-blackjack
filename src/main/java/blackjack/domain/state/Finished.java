package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public abstract class Finished extends Started {

    protected static final double BLACKJACK_WIN_RATE = 1.5;
    protected  static final int WIN_RATE = 1;
    protected static final int TIE_RATE = 0;
    protected static final int LOW_RATE = -1;

    protected Finished(Cards cards) {
        super(cards);
    }

    @Override
    public final State draw(Card card) {
        throw new IllegalStateException();
    }

    @Override
    public final State stay() {
        throw new IllegalStateException();
    }

    @Override
    public final boolean isRunning() {
        return false;
    }

    @Override
    public final boolean isFinished() {
        return true;
    }
}
