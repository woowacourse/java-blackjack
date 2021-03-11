package blackjack.domain.state;

import blackjack.domain.card.UserDeck;

public abstract class BasicState implements State {
    private UserDeck userDeck;

    public BasicState(UserDeck userDeck) {
        this.userDeck = userDeck;
    }

    public UserDeck getUserDeck() {
        return this.userDeck;
    }

    public int getPoint() {
        return this.userDeck.score();
    }
}
