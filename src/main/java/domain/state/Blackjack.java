package domain.state;

import domain.Result;
import domain.card.Hand;

public class Blackjack extends State {
    public Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    public Result judge(State state) {
        if (state instanceof Blackjack) {
            return Result.DRAW;
        }
        return Result.BLACKJACK;
    }
}
