package blackjack.model.state;

import blackjack.model.participant.Hand;

public class BustState extends FinalState {

    public BustState(Hand hand) {
        super(hand);
    }

    @Override
    public double getProfitWeight() {
        return 1;
    }

    @Override
    public boolean isStateOf(StateValue stateValue) {
        return StateValue.BUST.equals(stateValue);
    }
}
