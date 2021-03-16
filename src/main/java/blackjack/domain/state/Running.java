package blackjack.domain.state;

import blackjack.domain.user.Money;

public abstract class Running implements State {

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double earning(Money money) {
        throw new UnsupportedOperationException();
    }
}
