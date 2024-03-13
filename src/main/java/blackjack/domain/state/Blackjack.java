package blackjack.domain.state;

import blackjack.domain.card.CardHand;

public class Blackjack extends Finished {

    public Blackjack(final CardHand cardHand) {
        super(cardHand);
    }
}
