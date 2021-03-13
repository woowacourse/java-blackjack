package blackjack.domain.state;

import blackjack.domain.carddeck.Card;

import java.util.ArrayList;
import java.util.List;

public class StateFactory {

    private StateFactory() {
    }

    public static State initHand(final List<Card> cards) {
        Hand hand = new Hand(new ArrayList<>(cards));
        if (hand.isBlackjack()) {
            return new Blackjack(hand);
        }
        return new Hit(hand);
    }
}
