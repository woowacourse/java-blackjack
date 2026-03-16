package domain.state;

import domain.card.Card;
import domain.card.Hand;

public class Hit extends State {
    public Hit(Hand hand) {
        super(hand);
    }

    public static State of(Hand hand) {
        if (hand.isBlackjack()) {
            return new Blackjack(hand);
        }
        return new Hit(hand);
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
    public boolean isFinished() {
        return false;
    }
}
