package blakjack.domain.state;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.card.Card;

public abstract class State {
    final protected PrivateArea privateArea;
    final protected Chip chip;

    protected State(final PrivateArea privateArea, final Chip chip) {
        this.privateArea = privateArea;
        this.chip = chip;
    }

    public abstract State draw(Card card);

    public abstract State stay();

    public abstract State compare(State dealerState);

    public final boolean isBust() {
        return privateArea.isBust();
    }
}
