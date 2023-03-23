package domain.state;

import domain.card.Hand;
import exception.IllegalToGetProfitRatioInNotFinishedException;
import exception.IllegalToStayInReadyException;

public abstract class Ready extends State {
    Ready(Hand hand) {
        super(hand);
    }

    @Override
    public State stay() {
        throw new IllegalToStayInReadyException();
    }

    @Override
    public double getProfitRatio() {
        throw new IllegalToGetProfitRatioInNotFinishedException();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
