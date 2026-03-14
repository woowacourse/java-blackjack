package domain.state;

import domain.card.Card;
import domain.participant.Hand;

public class Hit extends Running {
    public Hit(Hand hand) {
        super(hand);
    }

    @Override
    public State draw(Card card) {
        getHand().add(card);
        if (getHand().isBust()) {
            return new Bust(getHand());
        }
        if (getHand().isBlackjack()) {
            return new Blackjack(getHand());
        }
        return new Hit(getHand());
    }

    @Override
    public State stay() {
        return new Stay(getHand());
    }
}
