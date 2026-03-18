package domain.state;

import domain.Result;
import domain.Score;
import domain.card.Hand;

public class Stay extends Finished {
    public Stay(Hand hand) {
        super(hand);
    }

    @Override
    public Result judge(State state) {
        if (state.hand().isBlackjack()) {
            return Result.LOSE;
        }
        if (state.hand().isBust()) {
            return Result.WIN;
        }
        return judgeByScore(state);
    }

    private Result judgeByScore(State state) {
        Score sum = hand.totalSum();
        Score targetSum = state.hand().totalSum();

        if (sum.isEqualTo(targetSum)) {
            return Result.DRAW;
        }
        if (sum.isGreaterThan(targetSum)) {
            return Result.WIN;
        }
        return Result.LOSE;
    }
}
