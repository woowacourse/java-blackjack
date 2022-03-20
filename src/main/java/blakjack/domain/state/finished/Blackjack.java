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
    public State compare(State other) {
        if (privateArea.isDealer()) {
            return compareWithPlayer(other);
        }
        return compareWithDealer(other);
    }

    private State compareWithPlayer(final State playerState) {
        if (playerState.isBlackjack()) {
            return new Draw(privateArea, chip);
        }
        return new Win(privateArea, playerState.getChip());
    }

    private State compareWithDealer(final State dealerState) {
        if (dealerState.isBlackjack()) {
            return new Draw(privateArea, chip);
        }
        return new BlackjackWin(privateArea, chip);
    }
}
