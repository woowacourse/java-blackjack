package blackjack.model.state;

import blackjack.model.participant.Hand;

public abstract class DrawState extends State {

    public DrawState(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isStateOf(StateValue stateValue) {
        return StateValue.DRAW.equals(stateValue);
    }
}
