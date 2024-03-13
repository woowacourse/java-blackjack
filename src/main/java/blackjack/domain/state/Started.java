package blackjack.domain.state;

import blackjack.domain.card.CardHand;

public abstract class Started implements State {
    private final CardHand cardHand;

    public Started(final CardHand cardHand) {
        this.cardHand = cardHand;
    }

    @Override
    public int calculateScore() {
        return cardHand.sumAllScore();
    }
}
