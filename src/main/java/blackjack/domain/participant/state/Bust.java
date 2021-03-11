package blackjack.domain.participant.state;

import blackjack.domain.Result;

public class Bust extends Finished {

    public Bust(final Hand hand) {
        super(hand);
    }

    @Override
    public Result calculatePlayerResult(State dealerState) {
        return Result.LOSE;
    }
}
