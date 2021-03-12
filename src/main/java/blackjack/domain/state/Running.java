package blackjack.domain.state;

import blackjack.domain.card.Cards;

public abstract class Running extends Started {
    protected Running(Cards cards) {
        super(cards);
    }

    @Override
    public boolean isFinish() {
        return false;
    }
}
