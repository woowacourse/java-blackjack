package blakjack.domain.state.finished;

import blakjack.domain.Chip;
import blakjack.domain.PrivateArea;
import blakjack.domain.state.State;
import blakjack.domain.state.gameresult.Draw;
import blakjack.domain.state.gameresult.Lose;
import blakjack.domain.state.gameresult.Win;

public final class Stay extends Finished {
    public Stay(final PrivateArea privateArea, final Chip chip) {
        super(privateArea, chip);
    }

    @Override
    public State compare(final State other) {
        if (other.isBlackjack()) {
            return new Lose(privateArea, chip);
        }
        if (other.isBust()) {
            return new Win(privateArea, chip);
        }
        return compareByScore(other.getTotalScore());
    }

    private State compareByScore(final int other) {
        if (getTotalScore() == other) {
            return new Draw(privateArea, chip);
        }
        if (getTotalScore() > other) {
            return new Win(privateArea, chip);
        }
        return new Lose(privateArea, chip);
    }
}
