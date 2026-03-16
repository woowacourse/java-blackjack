package domain.state;

import domain.Result;
import domain.card.Card;
import domain.card.Hand;

public class Bust extends State {
    public Bust(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        hand.add(card);
        return new Bust(hand);
    }

    @Override
    public State stay() {
        return new Bust(hand);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Result judge(State state) {
        return Result.LOSE;
    }

}
