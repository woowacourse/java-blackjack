package blackjack.model.state;

import blackjack.model.participant.Hand;

public class BlackjackState extends FinalState {

    public BlackjackState(Hand hand) {
        super(hand);
    }

    @Override
    public double getProfitWeight() {
        return 1.5;
    }

    @Override
    public boolean isStateOf(StateValue stateValue) {
        return StateValue.BLACKJACK.equals(stateValue);
    }

}
