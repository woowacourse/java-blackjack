package blackjack.domain.state;

import blackjack.domain.card.CardHand;

public class Stay extends Finished {

    public Stay(final CardHand cardHand) {
        super(cardHand);
    }
}
