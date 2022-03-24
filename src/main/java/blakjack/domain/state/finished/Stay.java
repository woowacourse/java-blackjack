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
            return createLose(other);
        }
        if (other.isBust()) {
            return createWin(other);
        }
        return compareByScore(other);
    }

    private State createLose(final State other) {
        if (privateArea.isDealer()) {
            return new Lose(privateArea, other.getChip());
        }
        return new Lose(privateArea, chip);
    }

    private State createWin(final State other) {
        if (privateArea.isDealer()) {
            return new Win(privateArea, other.getChip());
        }
        return new Win(privateArea, chip);
    }

    private State compareByScore(final State other) {
        if (getTotalScore() == other.getTotalScore()) {
            return new Draw(privateArea, chip);
        }
        if (getTotalScore() > other.getTotalScore()) {
            return createWin(other);
        }
        return createLose(other);
    }
}
