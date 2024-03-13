package blackjack.domain.state;

import blackjack.domain.card.CardHand;

public class Bust extends Finished {

    public Bust(final CardHand cardHand) {
        super(cardHand);
    }
}
