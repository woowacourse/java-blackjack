package blakjack.domain.state.finished;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.card.Card;
import blakjack.domain.state.State;

public abstract class Finished extends State {
    Finished(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }

    @Override
    final public State draw(Card card) {
        throw new IllegalStateException();
    }

    @Override
    final public State stay() {
        throw new IllegalStateException();
    }

    @Override
    public int getProfit() {
        throw new IllegalStateException();
    }
}
