package blackjack.domain.state;

import blackjack.domain.Hand;

public class StateFactory {
    private StateFactory() {
    }

    public static State getInstance(Hand hand) {
        if (hand.isBlackJack()) {
            return new BlackJackState();
        }
        return new Hit();
    }

    public static State getInstance() {
        return new NotStarted();
    }
}
