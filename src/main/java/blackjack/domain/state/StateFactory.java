package blackjack.domain.state;

import blackjack.domain.card.UserDeck;

public class StateFactory {

    public static State draw(UserDeck userDeck) {
        if (userDeck.isBlackjack()) {
            return new Blackjack();
        }
        if (userDeck.isBust()) {
            return new Bust();
        }
        return new Hit();
    }
}
