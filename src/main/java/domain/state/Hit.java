package domain.state;

import domain.card.Card;
import domain.card.Hand;

public class Hit extends Running {
    private Hit(Hand hand) {
        super(hand);
    }

    public static State of(Hand hand) {
        if (hand.isBlackjack()) {
            return new Blackjack(Hand.mutableCopyOf(hand));
        }
        return new Hit(Hand.mutableCopyOf(hand));
    }

    @Override
    public State draw(Card card) {
        hand.add(card);
        if (hand.isBust()) {
            return new Bust(Hand.mutableCopyOf(hand));
        }
        return new Hit(Hand.mutableCopyOf(hand));
    }

    @Override
    public State stay() {
        return new Stay(Hand.mutableCopyOf(hand));
    }
}
