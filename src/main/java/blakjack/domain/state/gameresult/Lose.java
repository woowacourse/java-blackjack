package blakjack.domain.state.gameresult;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;

public final class Lose extends GameResult {
    public Lose(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }
}
