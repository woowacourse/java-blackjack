package domain.state.type;

import domain.deck.Card;
import domain.gamer.Hand;
import domain.state.Finished;
import domain.state.State;

public class Stay extends Finished implements State {

    public Stay(final Hand hand) {
        super(hand);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public State hit(final Card card) {
        return super.hit(card);
    }

    @Override
    public State stay() {
        return super.stay();
    }
}
