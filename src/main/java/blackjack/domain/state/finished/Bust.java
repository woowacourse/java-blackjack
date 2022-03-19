package blackjack.domain.state.finished;

import blackjack.domain.card.HoldingCards;

public final class Bust extends Finished {

    public Bust(HoldingCards holdingCards) {
        super(holdingCards);
    }
}
