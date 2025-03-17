package domain.state.type;

import domain.deck.Card;
import domain.gamer.Hand;
import domain.state.Finished;
import domain.state.State;

public class Blackjack extends Finished {

    public Blackjack(final Hand hand) {
        super(hand);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State hit(final Card card) {
        throw new IllegalArgumentException("종료 상태입니다.");
    }

    @Override
    public State stay() {
        throw new IllegalArgumentException("종료 상태입니다.");
    }
}
