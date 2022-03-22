package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Running extends Started {

    protected Running(Cards cards) {
        super(cards);
    }

    @Override
    public final State stay() {
        return new Stay(getCards());
    }

    @Override
    public final boolean isRunning() {
        return true;
    }
}
