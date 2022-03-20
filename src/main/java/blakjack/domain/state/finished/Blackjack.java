package blakjack.domain.state.finished;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.state.State;
import blakjack.domain.state.gameresult.BlackjackWin;
import blakjack.domain.state.gameresult.Draw;
import blakjack.domain.state.gameresult.Win;

public final class Blackjack extends Finished {
    public Blackjack(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }

    @Override
    public State compare(final State other) {
        if (other.isBlackjack()) {
            return new Draw(privateArea, chip);
        }
        return createWin(other);
    }

    private State createWin(State other) {
        if (privateArea.isDealer()) {
            return new Win(privateArea, other.getChip());
        }
        return new BlackjackWin(privateArea, chip);
    }

}
