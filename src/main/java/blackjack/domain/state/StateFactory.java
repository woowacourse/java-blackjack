package blackjack.domain.state;

import blackjack.domain.participant.Hand;

public class StateFactory {

    private StateFactory() {
    }

    public static State initState(Hand hand) {
        if (hand.isBlackjack()) {
            return new Blackjack();
        }
        return new Hit();
    }
}
