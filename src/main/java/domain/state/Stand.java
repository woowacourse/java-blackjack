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
        if (dealerState.isBlackJack()) {
            return Result.LOSE;
        }
        if (dealerState.isBust()) {
            return Result.WIN;
        }
        return compareScore(dealerState);
    }

    private Result compareScore(final State dealerState) {
        int playerScore = this.calculateScore().getValue();
        int dealerScore = dealerState.calculateScore().getValue();
        if (playerScore > dealerScore) {
            return Result.WIN;
        }
        if (playerScore < dealerScore) {
            return Result.LOSE;
        }
        return Result.TIE;
    }
}
