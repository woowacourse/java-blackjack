package domain.state;

import domain.card.Card;
import domain.card.Hand;
import exception.IllegalToDrawInFinishedException;
import exception.IllegalToStayInFinishedException;

public abstract class Finished extends State {
    Finished(Hand hand) {
        super(hand);
    }

    @Override
    public final State draw(Card card) {
        throw new IllegalToDrawInFinishedException();
    }

    @Override
    public final State stay() {
        throw new IllegalToStayInFinishedException();
    }

    @Override
    public final boolean isFinished() {
        return true;
    }
}
