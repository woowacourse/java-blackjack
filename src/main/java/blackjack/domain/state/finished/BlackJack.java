package blackjack.domain.state.finished;

import blackjack.domain.card.HoldingCards;

public final class BlackJack extends Finished {

    public BlackJack(HoldingCards holdingCards) {
        super(holdingCards);
    }

    @Override
    public boolean isBust() {
        throw new IllegalStateException();
    }
}
