package blackjack.domain.participant.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

public final class Hit extends Running {

    Hit(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public State draw(final Card card) {
        final CardHand cardHand = receiveCard(card);

        if (cardHand.isBust()) {
            return new Bust(cardHand);
        }
        return new Hit(cardHand);
    }

    @Override
    public State stay() {
        return new Stay(getCardHand());
    }
}
