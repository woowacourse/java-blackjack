package blakjack.domain.state.gameresult;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;

public final class Draw extends GameResult {
    public Draw(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }
}
