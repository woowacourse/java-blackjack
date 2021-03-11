package blackjack.domain.participant.state;

import blackjack.domain.Result;

public class Blackjack extends Finished {

    public Blackjack(final Hand hand) {
        super(hand);
    }

    @Override
    public Result calculatePlayerResult(final State dealerState) {
        if (dealerState.score().isBlackjack()) {
            return Result.DRAW;
        }
        return Result.WIN;
    }
}
