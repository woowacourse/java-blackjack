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
        if (privateArea.isDealer()) {
            return compareWithPlayer(other);
        }
        return compareWithDealer(other);
    }

    // 이 부분을 어떻게 리펙토링하면 좋을까?
    private State compareWithPlayer(final State playerState) {
        if (playerState.isBlackjack()) {
            return new Lose(privateArea, playerState.getChip());
        }
        if (playerState.isBust()) {
            return new Win(privateArea, playerState.getChip());
        }
        return compareByScore(playerState);
    }

    private State compareByScore(final State playerState) {
        if (getTotalScore() == playerState.getTotalScore()) {
            return new Draw(privateArea, chip);
        }
        if (getTotalScore() > playerState.getTotalScore()) {
            return new Win(privateArea, playerState.getChip());
        }
        return new Lose(privateArea, playerState.getChip());
    }

    private State compareWithDealer(final State dealerState) {
        if (dealerState.isBlackjack()) {
            return new Lose(privateArea, chip);
        }
        if (dealerState.isBust()) {
            return new Win(privateArea, chip);
        }
        return compareByScore(dealerState.getTotalScore());
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
