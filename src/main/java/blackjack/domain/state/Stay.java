package blackjack.domain.state;

import blackjack.domain.card.CardHand;

public final class Stay extends Finished {

    public Stay(final CardHand cardHand) {
        super(cardHand);
    }
}
