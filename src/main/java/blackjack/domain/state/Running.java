package blackjack.domain.state;

import blackjack.domain.user.Money;

public abstract class Running implements State {

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profit(Money money) {
        throw new UnsupportedOperationException();
    }
}
