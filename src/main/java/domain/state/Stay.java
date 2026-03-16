package domain.state;

import domain.Result;
import domain.Score;
import domain.card.Hand;

public class Stay extends State {
    public Stay(Hand hand) {
        super(hand);
    }

    @Override
    public Result judge(State state) {
        if (state instanceof Blackjack) {
            return Result.LOSE;
        }
        if (state instanceof Bust) {
            return Result.WIN;
        }
        Score sum = hand.totalSum();
        Score targetSum = state.hand.totalSum();

        if (sum.isEqualTo(targetSum)) {
            return Result.DRAW;
        }
        if (sum.isGreaterThan(targetSum)) {
            return Result.WIN;
        }
        return Result.LOSE;
    }
}
