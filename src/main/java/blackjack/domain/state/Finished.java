package blackjack.domain.state;

import blackjack.domain.Hand;
import blackjack.domain.participant.Dealer;

public abstract class Finished implements State {
    ResultType resultType;

    @Override
    public State update(Hand hand) {
        throw new IllegalStateException();
    }

    @Override
    public State stay() {
        throw new IllegalStateException();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

    @Override
    public double profitRate(Dealer dealer, int score) {
        setState(dealer, score);
        return this.resultType.getProfitRate();
    }

    abstract void setState(Dealer dealer, int score);
}
