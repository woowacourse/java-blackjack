package blakjack.domain.state.finished;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.state.State;

public final class Stay extends Finished {
    public Stay(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }

    @Override
    public State compare(State dealerState) {
        return null;
    }
}
