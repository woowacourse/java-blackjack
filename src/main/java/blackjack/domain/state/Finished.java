package blackjack.domain.state;

import blackjack.domain.Hand;
import blackjack.domain.ResultType;

public abstract class Finished extends Started {
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
    public double profitRate(ResultType match) {
        return match.getProfitRate();
    }
}
