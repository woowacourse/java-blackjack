package domain.state.finished;

import domain.participants.Hand;
import domain.state.Result;
import java.util.function.Function;

public class Stay extends Finished {
    public Stay(Hand hand) {
        super(hand);
    }

    @Override
    public Function<Integer, Integer> earningRate() {
        return (n) -> n;
    }

    @Override
    public Result getResult(Finished dealerState) {
        if (dealerState instanceof Bust
                || dealerState.getScore() < getScore()) {
            return Result.WIN;
        }
        if (dealerState.getScore() > getScore()) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

}

