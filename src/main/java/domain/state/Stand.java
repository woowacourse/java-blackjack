package domain.state;

import domain.Result;

public class Stand extends Finished {

    public Stand(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackJack() {
        return false;
    }

    @Override
    public Result calculateResult(State dealerState) {
        return null;
    }
}
