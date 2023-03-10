package blackjack.model.state;

import blackjack.model.participant.Hand;

public class StandState extends FinalState {

    public StandState(Hand hand) {
        super(hand);
    }

    @Override
    public double getProfitWeight() {
        return 1;
    }
}
