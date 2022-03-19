package blackjack.domain.state.running;

import blackjack.domain.card.HoldingCards;
import blackjack.domain.state.State;

public abstract class Running implements State {

    private final HoldingCards holdingCards;

    protected Running(HoldingCards holdingCards) {
        this.holdingCards = holdingCards;
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    public final HoldingCards holdingCards() {
        return holdingCards;
    }
}
