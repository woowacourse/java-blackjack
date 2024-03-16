package domain.state.fininsh;

import domain.card.Hands;
import domain.player.Result;
import domain.state.State;

public final class Blackjack extends Finished {
    public Blackjack(final Hands hands) {
        super(hands);
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public double earningRate() {
        return 1.5;
    }

    @Override
    public Result compareWith(final State otherState) {
        if (!otherState.isFinished()) {
            throw new IllegalStateException();
        }
        final Finished otherFinished = (Finished) otherState;

        if (otherFinished.isBlackjack()) {
            return Result.TIE;
        }
        return Result.WIN;
    }

}
