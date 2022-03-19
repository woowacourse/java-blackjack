package blackjack.domain.state;

import blackjack.domain.card.HoldingCards;

public abstract class Started implements State {

    private final HoldingCards holdingCards;

    public Started(HoldingCards holdingCards) {
        this.holdingCards = holdingCards;
    }

    @Override
    public final HoldingCards holdingCards() {
        return holdingCards;
    }

    @Override
    public final int cardScore() {
        return holdingCards.cardScore();
    }
}
