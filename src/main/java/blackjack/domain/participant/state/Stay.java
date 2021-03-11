package blackjack.domain.participant.state;

import blackjack.domain.Result;

public class Stay extends Finished {

    public Stay(final Hand hand) {
        super(hand);
    }

    @Override
    public Result calculatePlayerResult(State dealerState) {
        if (dealerState.score().isBlackjack()) {
            return Result.LOSE;
        }
        if (dealerState.score().isBust()) {
            return Result.WIN;
        }
        return this.score().compare(dealerState.score());
    }
}
