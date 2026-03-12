package domain.state.finished;

import domain.participants.Hand;
import domain.state.Result;
import java.util.function.Function;

public class Bust extends Finished {
    public Bust(Hand hand) {
        super(hand);
    }

    @Override
    public Function<Integer, Integer> earningRate() {
        return (n) -> -n;
    }

    @Override
    public Result getResult(Finished dealerState) {
        return Result.LOSE;
    }
}
