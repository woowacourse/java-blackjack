package blackjack.domain.state;

import blackjack.domain.Hand;

public class StateFactory {

    public static State getInstance(Hand hand) {
        if (hand.getScore() == 21) {
            return new BlackJackState();
        }
        return null;
    }
}
