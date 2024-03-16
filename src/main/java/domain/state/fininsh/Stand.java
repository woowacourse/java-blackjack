package domain.state.fininsh;

import domain.card.Hands;
import domain.player.Result;
import domain.state.State;

public final class Stand extends Finished {
    public Stand(final Hands hands) {
        super(hands);
    }

    @Override
    public Result compareWith(final State otherState) {
        if (!otherState.isFinished()) {
            throw new ClassCastException("완료된 상태가 아닙니다");
        }
        final Finished otherFinished = (Finished) otherState;

        if (otherFinished.isBust()) {
            return Result.WIN;
        }
        if (otherFinished.isBlackjack()) {
            return Result.LOSE;
        }
        return compareScoreWith(otherState);
    }

    private Result compareScoreWith(final State state) {
        if (getScore() == state.getScore()) {
            return Result.TIE;
        }
        if (getScore() < state.getScore()) {
            return Result.LOSE;
        }
        return Result.WIN;
    }
}
