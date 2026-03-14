package domain.state.finished;

import domain.card.Hand;
import domain.state.Result;
import domain.state.State;
import java.util.function.Function;

public class Bust extends Finished {
    private static final int BUST_SCORE_BOUND = 21;

    public Bust(Hand hand) {
        super(hand);
    }

    public static boolean isBust(Hand hand) {
        return hand.getScore() > BUST_SCORE_BOUND;
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
