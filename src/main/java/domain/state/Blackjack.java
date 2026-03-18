package domain.state;

import domain.Result;
import domain.card.Hand;

public class Blackjack extends Finished {
    public Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    public Result judge(State state) {
        if (state.hand().isBlackjack()) {
            return Result.DRAW;
        }
        return Result.BLACKJACK;
    }
}
