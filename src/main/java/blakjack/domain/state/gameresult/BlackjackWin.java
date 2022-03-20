package blakjack.domain.state.gameresult;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;

public class BlackjackWin extends GameResult {
    public BlackjackWin(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }
}
