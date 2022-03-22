package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

public class Hit extends Running {

    protected Hit(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        hand.add(card);
        if (hand.isBust()) {
            return new Bust(hand);
        }
        return new Hit(hand);
    }

    @Override
    public State stay() {
        return new Stay(hand);
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
