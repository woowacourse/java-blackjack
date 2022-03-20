package blakjack.domain.state.finished;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.state.State;
import blakjack.domain.state.gameresult.Lose;
import blakjack.domain.state.gameresult.Win;

public final class Bust extends Finished {
    public Bust(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }

    @Override
    public State compare(final State other) {
        if (privateArea.isDealer()) {
            return compareWithPlayer(other);
        }
        return compareWithDealer();
    }

    private State compareWithPlayer(final State playerState) {
        if (playerState.isBust()) {
            return new Win(privateArea, chip);
        }
        return new Lose(privateArea, chip);
    }

    private State compareWithDealer() {
        return new Lose(privateArea, chip);
    }
}
