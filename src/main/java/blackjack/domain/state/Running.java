package blackjack.domain.state;

import blackjack.domain.card.Hand;

public abstract class Running extends Started {
    protected Running(Hand cards) {
        super(cards);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
