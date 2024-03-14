package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

public final class Hit extends Started {

    public Hit(final CardHand cardHand) {
        super(cardHand);
    }

    @Override
    public State draw(final Card card) {
        final CardHand cardHand = getCardHand();
        cardHand.receiveCard(card);

        if (cardHand.isBlackjack()) {
            return new Blackjack(cardHand);
        }
        if (cardHand.isBust()) {
            return new Bust(cardHand);
        }
        return new Hit(cardHand);
    }

    @Override
    public State stay() {
        return new Stay(getCardHand());
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
