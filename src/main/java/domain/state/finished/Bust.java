package domain.state.finished;

import domain.card.Hand;
import domain.state.Result;
import domain.state.State;
import java.util.function.Function;

public class Bust extends Finished {
    public Bust(Hand hand) {
        super(hand);
    }

    @Override
    public Function<Integer, Integer> earningRate(Result result) {
        return (n) -> -n;
    }

    @Override
    public Result getResult(State dealerState) {
        return Result.LOSE;
    }
}
