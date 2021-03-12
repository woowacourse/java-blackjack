package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Running extends Started {
    protected Running(Cards cards) {
        super(cards);
    }

    @Override
    public final boolean isFinish() {
        return false;
    }

    @Override
    public final State stay() {
        return new Stay(cards());
    }
}
