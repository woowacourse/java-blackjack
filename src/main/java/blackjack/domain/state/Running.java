package blackjack.domain.state;

import blackjack.domain.user.Cards;

public abstract class Running extends Started {
    protected Running(Cards cards) {
        super(cards);
    }

    @Override
    public final boolean isFinish() {
        return false;
    }
}
