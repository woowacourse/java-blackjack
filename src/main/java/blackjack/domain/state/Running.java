package blackjack.domain.state;

import blackjack.domain.card.PlayerCards;

public abstract class Running extends Started {
    protected Running(PlayerCards cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
