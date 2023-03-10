package blackjack.model.state;

import blackjack.model.participant.Hand;

public abstract class FinalState extends State {

    public FinalState(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    abstract public double getProfitWeight();

}
