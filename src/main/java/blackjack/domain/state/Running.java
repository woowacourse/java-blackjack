package blackjack.domain.state;

import blackjack.domain.card.Cards;
import blackjack.domain.money.Money;

public abstract class Running extends Started {
    public Running(final Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public double profit(final Money money) {
        throw new UnsupportedOperationException();
    }
}
