package domain.state.finished;

import domain.card.Hand;
import domain.state.Result;
import domain.state.State;
import java.util.function.Function;

public class Stay extends Finished {
    public Stay(Hand hand) {
        super(hand);
    }

    @Override
    public Function<Integer, Integer> earningRate(Result result) {
        if (Result.WIN.equals(result)) {
            return (n) -> n;
        }
        if (Result.DRAW.equals(result)) {
            return (n) -> 0;
        }
        if (Result.LOSE.equals(result)) {
            return (n) -> -n;
        }
        throw new IllegalStateException("Stay의 earningRate 잘못된 접근입니다.");
    }

    @Override
    public Result getResult(State dealerState) {
        if (dealerState instanceof Bust
                || this.getScore().isHigher(dealerState.getScore())) {
            return Result.WIN;
        }
        if (dealerState.getScore().isHigher(this.getScore())) {
            return Result.LOSE;
        }
        return Result.DRAW;
    }

}

