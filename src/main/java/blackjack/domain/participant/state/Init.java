package blackjack.domain.participant.state;

import blackjack.domain.carddeck.Card;
import java.util.Arrays;

public class Init {

    public static State draw(final Card firstCard, final Card secondCard) {
        Hand hand = new Hand(Arrays.asList(firstCard, secondCard));
        if (hand.isBlackjack()) {
            return new Blackjack(hand);
        }
        return new Hit(hand);
    }
}
