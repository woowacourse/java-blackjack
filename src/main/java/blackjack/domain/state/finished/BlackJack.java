package blackjack.domain.state.finished;

import blackjack.domain.card.HoldingCards;

public final class BlackJack extends Finished {

    public BlackJack(HoldingCards holdingCards) {
        super(holdingCards);
    }
}
