package domain.state;

import domain.card.Hand;

public abstract class Running extends State{
    public Running(Hand hand) {
        super(hand);
    }

    @Override
    public State stay() {
        return new Stay(getHand());
    }
}
