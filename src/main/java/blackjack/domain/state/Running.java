package blackjack.domain.state;

import blackjack.domain.ResultType;

public abstract class Running extends Started {

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profitRate(ResultType match) {
        throw new IllegalStateException();
    }
}
