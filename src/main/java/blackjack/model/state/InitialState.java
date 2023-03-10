package blackjack.model.state;

import blackjack.model.participant.Hand;

public abstract class InitialState extends State {

    protected static final int PICK_COUNT = 2;

    public InitialState(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public boolean isStateOf(StateValue stateValue) {
        return StateValue.INITIAL.equals(stateValue);
    }
}
