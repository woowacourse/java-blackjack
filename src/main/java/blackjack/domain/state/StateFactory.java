package blackjack.domain.state;

import blackjack.domain.card.Hand;

public class StateFactory {
    public static State draw(Hand hand) {
        if (hand.isBlackjack()) {
            return new Blackjack(hand);
        }
        return new Hit(hand);
    }
}
