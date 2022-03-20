package blakjack.domain.state.gameresult;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;

public final class Win extends GameResult {
    public Win(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }
}
