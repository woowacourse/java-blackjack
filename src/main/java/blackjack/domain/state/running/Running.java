package blackjack.domain.state.running;

import blackjack.domain.card.HoldingCards;
import blackjack.domain.state.Started;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Stand;

public abstract class Running extends Started {

    public Running(HoldingCards holdingCards) {
        super(holdingCards);
    }

    @Override
    public final boolean isFinished() {
        return false;
    }

    @Override
    public boolean isBust() {
        throw new IllegalStateException();
    }

    @Override
    public final State stand() {
        return new Stand(holdingCards());
    }
}
