package blackjack.domain.state;

import blackjack.domain.carddeck.Card;
import blackjack.domain.participant.Hand;

public class Hit extends Running{

    public Hit(Hand hand) {
        super(hand);
    }

    @Override
    public State receiveCard(Card card) {
        hand.addCard(card);
        if (hand.isBust()) {
            return new Bust(hand);
        }
        return new Hit(hand);
    }

    @Override
    public State stay() {
        return new Stay(hand);
    }
}
