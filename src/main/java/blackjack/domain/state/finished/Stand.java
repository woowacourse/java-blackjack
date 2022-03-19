package blackjack.domain.state.finished;

import blackjack.domain.card.HoldingCards;

public final class Stand extends Finished {

    protected Stand(HoldingCards holdingCards) {
        super(holdingCards);
    }
}
