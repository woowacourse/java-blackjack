package blakjack.domain.state.running;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.state.State;

public abstract class Running extends State {
    Running(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }

    @Override
    public final State compare(State dealerState) {
        throw new IllegalStateException();
    }
}
