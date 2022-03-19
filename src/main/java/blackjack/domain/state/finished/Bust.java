package blackjack.domain.state.finished;

import blackjack.domain.card.HoldingCards;

public final class Bust extends Finished {

    Bust(HoldingCards holdingCards) {
        super(holdingCards);
    }
}
