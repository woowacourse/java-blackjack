package blackjack.domain.state;

import blackjack.domain.user.Cards;

public abstract class Running extends Started {
    public Running(Cards cards) {
        super(cards);
    }

    @Override
    public final boolean isFinish() {
        return false;
    }
}
