package domain.state;

import domain.Result;

public class Bust extends Finished {

    public Bust(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isBust() {
        return true;
    }

    @Override
    public boolean isBlackJack() {
        return false;
    }

    @Override
    public Result calculateResult(final State dealerState) {
        return Result.LOSE;
    }
}
