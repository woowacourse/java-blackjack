package blackjack.domain.state;

import blackjack.domain.card.HoldingCards;

public abstract class Started implements State {

    private final HoldingCards holdingCards;

    public Started(HoldingCards holdingCards) {
        this.holdingCards = holdingCards;
    }

    @Override
    public HoldingCards cards() {
        return holdingCards;
    }

    @Override
    public final int cardSum() {
        return holdingCards.cardSum();
    }

    @Override
    public final boolean isBust() {
        return holdingCards.isBust();
    }
}
