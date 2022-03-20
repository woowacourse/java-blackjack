package blackjack.domain.user.state;

import blackjack.domain.card.Card;
import blackjack.domain.user.Hand;

public final class Hit extends Running {

    protected Hit(Hand hand) {
        super(hand);
    }

    @Override
    public State hit(Card card) {
        Hand added = hand.addCard(card);
        if (added.isBust()) {
            return new Bust(added);
        }
        return new Hit(added);
    }

    @Override
    public State stay() {
        return new Stay(hand);
    }
}
