package blackjack.domain.participant.state;

import blackjack.domain.carddeck.Card;

public class Hit extends Running {

    public Hit(final Hand hand) {
        super(hand);
    }

    public State draw(final Card card) {
        this.hand.addCard(card);
        if (this.hand.isBust()) {
            return new Bust(this.hand);
        }
        return new Hit(this.hand);
    }

    public State stay() {
        return new Stay(this.hand);
    }
}
