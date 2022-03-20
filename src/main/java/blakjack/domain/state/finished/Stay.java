package blakjack.domain.state.finished;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;

public class Stay extends Finished {
    public Stay(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }
}
