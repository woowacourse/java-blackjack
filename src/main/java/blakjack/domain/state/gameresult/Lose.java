package blakjack.domain.state.gameresult;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;

public final class Lose extends GameResult {
    private static final int RATE = -1;

    public Lose(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }

    @Override
    public int getProfit() {
        return RATE * chip.getValue();
    }
}
