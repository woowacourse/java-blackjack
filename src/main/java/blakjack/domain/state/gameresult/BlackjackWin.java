package blakjack.domain.state.gameresult;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;

public class BlackjackWin extends GameResult {
    private static final double RATE = 1.5;

    public BlackjackWin(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }

    @Override
    public int getProfit() {
        return (int) (RATE * chip.getValue());
    }
}
