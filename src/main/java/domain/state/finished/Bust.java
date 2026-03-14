package domain.state.finished;

import domain.card.Hand;
import domain.score.Score;
import domain.state.Result;
import domain.state.State;
import java.util.function.Function;

public class Bust extends Finished {
    private static final Score BUST_SCORE_BOUND = new Score(21);

    public Bust(Hand hand) {
        super(hand);
    }

    public static boolean isBust(Hand hand) {
        return hand.getScore().isHigher(BUST_SCORE_BOUND);
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
