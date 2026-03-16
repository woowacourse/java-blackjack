package domain.state;

import domain.Result;
import domain.card.Card;
import domain.card.Hand;

public class Blackjack extends State {
    public Blackjack(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        hand.add(card);
        return new Bust(hand);
    }

    @Override
    public State stay() {
        return new Blackjack(hand);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public Result judge(State state) {
        if (state instanceof Blackjack) {
            return Result.DRAW;
        }
        return Result.BLACKJACK;
    }
}
