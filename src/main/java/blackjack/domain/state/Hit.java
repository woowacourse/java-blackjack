package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

public final class Hit implements State {
    private final CardHand cardHand;

    public Hit(final CardHand cardHand) {
        this.cardHand = cardHand;
    }

    @Override
    public State draw(final Card card) {
        cardHand.receiveCard(card);
        return this;
    }

    @Override
    public int calculateScore() {
        return cardHand.sumAllScore();
    }
}
