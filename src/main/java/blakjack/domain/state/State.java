package blakjack.domain.state;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.card.Card;

public abstract class State {
    final PrivateArea privateArea;
    final Chip chip;

    protected State(PrivateArea privateArea, Chip chip) {
        this.privateArea = privateArea;
        this.chip = chip;
    }

    abstract State draw(Card card);
}
