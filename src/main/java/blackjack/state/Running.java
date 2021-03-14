package blackjack.state;

import blackjack.domain.Dealer;
import blackjack.domain.User;

public abstract class Running extends Started {

    public Running(Cards cards) {
        super(cards);
    }

    @Override
    public double profit(double money, User dealer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
