package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Running extends Started {

    Running(Cards cards) {
        super(cards);
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    @Override
    public final double profit(double money) {
        throw new IllegalStateException();
    }
}
