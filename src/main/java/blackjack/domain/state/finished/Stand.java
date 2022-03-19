package blackjack.domain.state.finished;

import blackjack.domain.card.HoldingCards;

public final class Stand extends Finished {

    public Stand(HoldingCards holdingCards) {
        super(holdingCards);
    }

    @Override
    public boolean isBust() {
        throw new IllegalStateException();
    }
}
