package domain.state;

import domain.Result;
import domain.card.Hand;

public class Bust extends Finished {
    public Bust(Hand hand) {
        super(hand);
    }

    @Override
    public Result judge(State state) {
        return Result.LOSE;
    }

}
